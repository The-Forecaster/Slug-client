package me.austin.queer.nameable.hack

import java.io.File
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import me.austin.queer.manager.managers.HackManager
import me.austin.queer.Globals.*
import me.austin.queer.nameable.Nameable
import me.austin.queer.util.file.FileHelper
import me.zero.alpine.listener.Listenable

abstract class Hack(name: String, val settings : MutableMap<String, Any> = mutableMapOf()): Nameable(name), Listenable {
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
        return enabled;
    }

    open fun onEnable() {}

    open fun onDisable() {}

    fun load() {
        try {
            if (!this.file.exists()) this.file.createNewFile()

            val hackobj = FileHelper.readJson(this.file.toPath())

            for (entry in this.settings) {
                val rawval = hackobj.get(entry.key).asString
                try {
                    val value = rawval.toBoolean()

                    this.settings.set(entry.key, value)
                }
                catch (e : Exception) {
                    try {
                        val value = rawval.toDouble()
                        
                        if (entry.value is Int) this.settings.set(entry.key, value.toInt())
                        else if (entry.value is Float) this.settings.set(entry.key, value.toFloat())
                        else this.settings.set(entry.key, value)
                    }
                    catch (e : Exception) {
                        
                    }
                }
            }
        }
        catch (e : Exception) {
            FileHelper.clearJson(this.file.toPath())

            LOGGER.error("$name failed to load")

            e.printStackTrace()
        }
    }

    fun save() {
        try {
            val json = JsonObject()
            for (setting in this.settings) {
                json.add(setting.key, JsonPrimitive(setting.value.toString()))
            }
            FileHelper.writeToJson(json, this.file.toPath())
        } catch (e: Exception) {
            LOGGER.error("Couldn't save $name", this.name)
            e.printStackTrace()
        }
    }
}
