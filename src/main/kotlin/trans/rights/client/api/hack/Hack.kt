package trans.rights.client.api.hack

import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import trans.rights.BasicEventManager
import trans.rights.TransRights.Companion.LOGGER
import trans.rights.client.api.Wrapper
import trans.rights.client.api.commons.Modular
import trans.rights.client.impl.setting.BooleanSetting
import trans.rights.client.impl.setting.EnumSetting
import trans.rights.client.impl.setting.NumberSetting
import trans.rights.client.impl.setting.Settings
import trans.rights.client.util.clearJson
import trans.rights.client.util.fromJson
import trans.rights.client.util.writeToJson
import java.io.File

abstract class Hack(name: String, description: String) : Modular(name, description), Wrapper {
    private val file: File = File("${HackManager.directory}$name.json")

    abstract val settings: Settings

    var enabled: Boolean = false

    protected fun enable() {
        if (!this.enabled) {
            BasicEventManager.register(this)

            this.onEnable()

            this.enabled = true
        }
    }

    protected fun disable() {
        if (this.enabled) {
            BasicEventManager.unregister(this)

            this.onDisable()

            this.enabled = false
        }
    }

    fun toggle() = if (this.enabled) disable() else enable()

    open fun onEnable() {}

    open fun onDisable() {}

    fun load(file: File = this.file) {
        try {
            if (!this.file.exists()) {
                this.file.createNewFile()
                return
            }

            if (file.fromJson().size() == 0) {
                this.save(file)
                return
            }

            val json = file.fromJson(true)

            this.enabled = json.get("enabled").asBoolean

            this.settings.allSettings.stream().forEach { setting ->
                when (setting) {
                    is BooleanSetting -> setting.set(json.get(setting.name).asBoolean)
                    is NumberSetting -> setting.set(json.get(setting.name).asDouble)
                    is EnumSetting -> setting.set(json.get(setting.name).asString)
                }
            }
        } catch (e: Exception) {
            file.clearJson()

            LOGGER.error("$name failed to load")

            e.printStackTrace()
        }
    }

    fun save(file: File = this.file) = try {
        JsonObject().let {

            it.add("enabled", JsonPrimitive(enabled))

            this.settings.allSettings.forEach { setting ->
                when (setting) {
                    is BooleanSetting -> it.add(setting.name, JsonPrimitive(setting.value))
                    is NumberSetting -> it.add(setting.name, JsonPrimitive(setting.value))
                    is EnumSetting -> it.add(setting.name, JsonPrimitive(setting.value.toString()))
                }
            }

            file.writeToJson(it)
        }
    } catch (e: Exception) {
        LOGGER.error("$name failed to save")

        e.printStackTrace()
    }
}
