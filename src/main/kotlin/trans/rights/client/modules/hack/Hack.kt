package trans.rights.client.modules.hack

import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import java.io.File
import me.zero.alpine.listener.Listenable
import trans.rights.client.Globals.*
import trans.rights.client.modules.Module
import trans.rights.client.util.file.*

abstract class Hack(
        name: String,
        description: String,
        val settings: MutableMap<String, Any> = mutableMapOf()
) : Module(name, description), Listenable {
    private val file: File
    private var enabled: Boolean = false

    init {
        this.settings.put("Enabled", enabled)

        file = File(HackManager.dir.absolutePath + this.name + ".json")
    }

    fun enable() {
        EVENTBUS.subscribe(this)

        this.enabled = true
    }

    fun disable() {
        EVENTBUS.unsubscribe(this)

        this.enabled = false
    }

    fun toggle() {
        if (this.enabled) disable() else enable()
    }

    fun isEnabled(): Boolean {
        return enabled
    }

    open fun onEnable() {}

    open fun onDisable() {}

    // This is dumb: find a better way to do this
    fun load() {
        try {
            if (!file.exists()) file.createNewFile()
            this.save()

            val hackobj = readJson(file.toPath())

            for (entry in settings) {
                val rawval = hackobj.get(entry.key).asString
                try {
                    val value = rawval.toBoolean()

                    settings.set(entry.key, value)
                } catch (e: Exception) {
                    try {
                        val value = rawval.toDouble()

                        if (entry.value is Int) settings.set(entry.key, value.toInt())
                        else if (entry.value is Float) settings.set(entry.key, value.toFloat())
                        else settings.set(entry.key, value)
                    } catch (e: Exception) {}
                }
            }
        } catch (e: Exception) {
            clearJson(file.toPath())

            LOGGER.error("$name failed to load")

            e.printStackTrace()
        }
    }

    fun save() {
        try {
            val json = JsonObject()
            for (setting in settings) {
                json.add(setting.key, JsonPrimitive(setting.value.toString()))
            }
            writeToJson(json, file.toPath())
        } catch (e: Exception) {
            LOGGER.error("Couldn't save $name", name)
            e.printStackTrace()
        }
    }
}
