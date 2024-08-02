package me.austin.client.api.hack

import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.mojang.brigadier.Command.SINGLE_SUCCESS
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType.getString
import com.mojang.brigadier.arguments.StringArgumentType.word
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder.literal
import com.mojang.brigadier.builder.RequiredArgumentBuilder.argument
import com.mojang.brigadier.exceptions.BuiltInExceptions
import me.austin.client.BasicEventManager
import me.austin.client.Slug.Companion.LOGGER
import me.austin.client.api.Wrapper
import me.austin.client.impl.command.arguments.getSetting
import me.austin.client.impl.command.arguments.setting
import me.austin.client.impl.setting.*
import me.austin.client.util.clearJson
import me.austin.client.api.Modular
import me.austin.client.api.setting.NumberSetting
import me.austin.client.impl.events.KeyEvent
import me.austin.client.util.clientSend
import me.austin.client.util.fromJson
import me.austin.client.util.writeToJson
import me.austin.rush.listener
import net.minecraft.command.CommandSource
import java.nio.file.Files
import java.nio.file.Path

abstract class Hack(name: String, description: String) : Modular(name, description), Wrapper {
    private val path = Path.of("${HackManager.directory}/$name.json")

    private val keyBind = IntSettingBuilder("KeyBind").default(0).description("If this key is pressed then the module will be toggled").build()

    private val keyListener = listener<KeyEvent> { event ->
        if (event.key == this.keyBind.value) {
            this.toggle()
            event.cancel()
        }
    }

    // This is the list of settings for the hack
    // if a setting isn't contained here then the client won't be able to find it
    open val settings = Settings(keyBind)

    // These will be registered every time this hack is enabled
    open val listeners = listOf(keyListener)

    var isEnabled = false
        private set

    private fun enable() {
        if (!this.isEnabled) {
            BasicEventManager.subscribeAll(this.listeners)

            this.onEnable()

            this.isEnabled = true
        }
    }

    protected fun disable() {
        if (this.isEnabled) {
            BasicEventManager.unsubscribeAll(this.listeners)

            this.onDisable()

            this.isEnabled = false
        }
    }

    fun toggle() {
        if (this.isEnabled) {
            disable()
        } else {
            enable()
        }
    }

    open fun onEnable() {}

    open fun onDisable() {}

    fun load(path: Path = this.path) {
        if (!Files.exists(path)) {
            Files.createFile(path)
            this.save()
            return
        }

        if (this.path.fromJson().size() == 0) {
            this.save()
            return
        }

        try {
            val json = this.path.fromJson(true)

            this.isEnabled = json.get("enabled").asBoolean

            for (setting in settings.allSettings()) {
                when (setting) {
                    is BooleanSetting -> setting.set(json.get(setting.name).asBoolean)
                    is DoubleSetting -> setting.set(json.get(setting.name).asDouble)
                    is FloatSetting -> setting.set(json.get(setting.name).asFloat)
                    is IntSetting -> setting.set(json.get(setting.name).asInt)
                    is LongSetting -> setting.set(json.get(setting.name).asLong)
                    is EnumSetting -> setting.set(json.get(setting.name).asString)
                }
            }
            if (this.isEnabled) {
                BasicEventManager.subscribeAll(this.listeners)
            }
        } catch (e: Exception) {
            this.path.clearJson()

            LOGGER.error("$name failed to load")

            e.printStackTrace()
        }
    }

    private fun save(path: Path = this.path) {
        try {
            JsonObject().let {

                it.add("enabled", JsonPrimitive(isEnabled))

                for (setting in this.settings.allSettings()) {
                    when (setting) {
                        is BooleanSetting -> it.add(setting.name, JsonPrimitive(setting.value))
                        is NumberSetting<*> -> it.add(setting.name, JsonPrimitive(setting.value))
                        is EnumSetting -> it.add(setting.name, JsonPrimitive(setting.value.toString()))
                    }
                }

                path.writeToJson(it)
            }
        } catch (e: Exception) {
            LOGGER.error("$name failed to save")

            e.printStackTrace()
        }
    }

    fun unload(path: Path = this.path) {
        if (this.isEnabled) BasicEventManager.unsubscribeAll(this.listeners)

        this.save(path)
    }

    fun register(dispatcher: CommandDispatcher<CommandSource>) {
        val builder = literal<CommandSource>(this.name).then(
            argument("setting", setting(this))
        ).executes { context ->
            clientSend("§a$name is set to ${getSetting(context, "setting", this)?.value}")

            SINGLE_SUCCESS
        }.then(argument("value", word())).executes {
            val input = getString(it, "value")
            val setting = getSetting(it, "setting", this) ?: throw BuiltInExceptions().dispatcherUnknownArgument().create()

            try {
                when (setting) {
                    is BooleanSetting -> setting.set(input.toBoolean())
                    is DoubleSetting -> setting.set(input.toDouble())
                    is FloatSetting -> setting.set(input.toFloat())
                    is IntSetting -> setting.set(input.toInt())
                    is LongSetting -> setting.set(input.toLong())
                    is EnumSetting -> if (!setting.set(input)) {
                        throw BuiltInExceptions().dispatcherUnknownArgument().create()
                    }
                    else -> {
                        clientSend("You cannot set that setting like this")
                        throw BuiltInExceptions().dispatcherUnknownArgument().create()
                    }
                }

                clientSend("§a${setting.name} set to $input")
            } catch (e: Exception) {
                throw BuiltInExceptions().dispatcherUnknownArgument().create()
            }

            SINGLE_SUCCESS
        }
        this.build(builder)
        dispatcher.register(builder)

    }

    open fun build(builder: LiteralArgumentBuilder<CommandSource>) {}
}
