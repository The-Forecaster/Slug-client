package trans.rights.api.hack

import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.mojang.brigadier.Command.SINGLE_SUCCESS
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.arguments.StringArgumentType.getString
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder.literal
import com.mojang.brigadier.builder.RequiredArgumentBuilder.argument
import com.mojang.brigadier.exceptions.BuiltInExceptions
import net.minecraft.command.CommandSource
import trans.rights.BasicEventManager
import trans.rights.Queer.Companion.LOGGER
import trans.rights.api.Wrapper
import trans.rights.impl.command.arguments.getSetting
import trans.rights.impl.command.arguments.setting
import trans.rights.impl.setting.*
import trans.rights.util.clearJson
import trans.rights.util.clientSend
import trans.rights.util.fromJson
import trans.rights.util.writeToJson
import me.austin.rush.Listener
import java.nio.file.Files
import java.nio.file.Path

abstract class Hack(name: String, description: String) : trans.rights.api.Modular(name, description),
    Wrapper {
    private val path: Path = Path.of("${HackManager.directory}/$name.json")

    open val settings = Settings()

    open val listeners = listOf<Listener<*>>()

    var isEnabled = false
        private set

    private fun enable() {
        if (!this.isEnabled) {
            BasicEventManager.registerAll(this.listeners)

            this.onEnable()

            this.isEnabled = true
        }
    }

    protected fun disable() {
        if (this.isEnabled) {
            BasicEventManager.unregisterAll(this.listeners)

            this.onDisable()

            this.isEnabled = false
        }
    }

    fun toggle() = if (this.isEnabled) disable() else enable()

    open fun onEnable() {}

    open fun onDisable() {}

    fun load(path: Path = this.path) {
        if (!Files.exists(path)) {
            Files.createFile(path)
            return
        }

        if (this.path.fromJson().size() == 0) {
            this.save(this.path)
            return
        }

        try {
            val json = this.path.fromJson(true)

            this.isEnabled = json.get("enabled").asBoolean

            for (setting in settings.allSettings) {
                when (setting) {
                    is BooleanSetting -> setting.set(json.get(setting.name).asBoolean)
                    is DoubleSetting -> setting.set(json.get(setting.name).asDouble)
                    is FloatSetting -> setting.set(json.get(setting.name).asFloat)
                    is IntSetting -> setting.set(json.get(setting.name).asInt)
                    is ShortSetting -> setting.set(json.get(setting.name).asShort)
                    is LongSetting -> setting.set(json.get(setting.name).asLong)
                    is EnumSetting -> setting.set(json.get(setting.name).asString)
                }
            }
            if (this.isEnabled) BasicEventManager.registerAll(this.listeners)
        } catch (e: Exception) {
            this.path.clearJson()

            LOGGER.error("$name failed to load")

            e.printStackTrace()
        }
    }

    private fun save(path: Path = this.path) = try {
        JsonObject().let {

            it.add("enabled", JsonPrimitive(isEnabled))

            for (setting in this.settings.allSettings) {
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

    fun unload(path: Path = this.path) {
        if (this.isEnabled) BasicEventManager.unregisterAll(this.listeners)

        this.save(path)
    }

    fun register(dispatcher: CommandDispatcher<CommandSource>) {
        dispatcher.register(
            this.build(literal<CommandSource>(this.name).then(
                argument("setting", setting(this))
            ).then(argument("value", StringArgumentType.word())).executes {
                val input = getString(it, "value")
                val setting = getSetting(it, "setting", this) ?: throw BuiltInExceptions().dispatcherUnknownArgument().create()

                try {
                    when (setting) {
                        is BooleanSetting -> setting.set(input.toBoolean())
                        is DoubleSetting -> setting.set(input.toDouble())
                        is FloatSetting -> setting.set(input.toFloat())
                        is IntSetting -> setting.set(input.toInt())
                        is ShortSetting -> setting.set(input.toShort())
                        is LongSetting -> setting.set(input.toLong())
                        is EnumSetting -> if (!setting.set(input)) throw BuiltInExceptions().dispatcherUnknownArgument()
                            .create()
                        else -> {
                            this.clientSend("You cannot set that setting like this")
                            throw BuiltInExceptions().dispatcherUnknownArgument().create()
                        }
                    }
                } catch (e: Exception) {
                    throw BuiltInExceptions().dispatcherUnknownArgument().create()
                }

                this.clientSend("§a${setting.name} set to $input")

                SINGLE_SUCCESS
            })
        )
    }

    open fun build(builder: LiteralArgumentBuilder<CommandSource>): LiteralArgumentBuilder<CommandSource> = builder
}
