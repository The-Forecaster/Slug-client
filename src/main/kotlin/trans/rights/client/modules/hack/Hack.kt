package trans.rights.client.modules.hack

import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import java.io.File
import trans.rights.client.TransRights.Companion.LOGGER
import trans.rights.client.modules.Module
import trans.rights.client.util.file.*

abstract class Hack(
        name: String,
        description: String,
        val settings: MutableMap<String, Any> = mutableMapOf()
) : Module(name, description) {
    val file: File
    private var enabled: Boolean = false

    init {
        this.settings["Enabled"] = enabled

        this.file = File(HackManager.dir.absolutePath + "$name.json")
    }

    fun enable() {
        this.eventBus.register(this)

        this.enabled = true
    }

    fun disable() {
        this.eventBus.unregister(this)

        this.enabled = false
    }

    fun toggle() {
        if (this.enabled) disable() else enable()
    }

    fun isEnabled(): Boolean {
        return this.enabled
    }

    open fun onEnable() {}

    open fun onDisable() {}

    // This is dumb: find a better way to do this
    fun load(file: File) {
        try {
            if (!file.exists()) file.createNewFile()
            this.save(file)

            val hackobj = readJson(file.toPath())

            for (entry in settings) {
                val rawval = hackobj.get(entry.key).asString
                try {
                    val value = rawval.toBoolean()

                    settings[entry.key] = value
                } 
                catch (e: Exception) {
                    try {
                        val value = rawval.toDouble()

                        settings[entry.key] = when (entry.value) {
                            is Int -> value.toInt()
                            is Float -> value.toFloat()
                            else -> value
                        }
                    } 
                    catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        } 
        catch (e: Exception) {
            clearJson(file.toPath())

            LOGGER.error("$name failed to load")

            e.printStackTrace()
        }
    }

    fun save(file: File) {
        try {
            val json = JsonObject()
            for (setting in settings) {
                json.add(setting.key, JsonPrimitive(setting.value.toString()))
            }
            writeToJson(json, file.toPath())
        } 
        catch (e: Exception) {
            LOGGER.error("Couldn't save $name", name)
            e.printStackTrace()
        }
    }
}
