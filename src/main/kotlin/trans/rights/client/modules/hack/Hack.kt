package trans.rights.client.modules.hack

import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import java.io.File
import trans.rights.client.TransRights.Companion.LOGGER
import trans.rights.client.manager.impl.HackManager
import trans.rights.client.manager.impl.Settings
import trans.rights.client.misc.api.Globals
import trans.rights.client.modules.Module
import trans.rights.client.modules.setting.settings.BooleanSetting
import trans.rights.client.modules.setting.settings.NumberSetting
import trans.rights.client.util.file.FileHelper
import trans.rights.event.bus.impl.BasicEventManager

abstract class Hack(
    name: String,
    description: String,
    val settings: Settings = Settings(),
    val file: File = File(HackManager.directory.absolutePath + "$name.json"),
    private var enabled: Boolean = false
) : Module(name, description), Globals {

    fun enable() {
        BasicEventManager.register(this)

        this.enabled = true
    }

    protected fun disable() {
        BasicEventManager.unregister(this)

        this.enabled = false
    }

    fun toggle() {
        if (this.enabled) disable() else enable()
    }

    open fun onEnable() {}

    open fun onDisable() {}

    fun load(file: File) {
        try {
            if (!file.exists()) file.createNewFile()

            if (FileHelper.read(this.file.toPath()) == "") {
                this.save(file)
                return
            }

            val json = FileHelper.fromJson(file.toPath())

            this.enabled = json.get("enabled").asBoolean

            for (setting in settings.values) {
                when (setting) {
                    is BooleanSetting -> setting.value = json.get(setting.name).asBoolean
                    is NumberSetting -> setting.value = json.get(setting.name).asDouble
                }
            }
        } catch (e: Exception) {
            FileHelper.clearJson(file.toPath())

            LOGGER.error("$name failed to load")

            e.printStackTrace()
        }
    }

    fun save(file: File) {
        try {
            val json = JsonObject()

            for (setting in settings.values) {
                json.add(setting.name, JsonPrimitive(setting.value.toString()))
            }

            FileHelper.writeToJson(json, file.toPath())
        } catch (e: Exception) {
            LOGGER.error("$name failed to save")

            e.printStackTrace()
        }
    }
}
