package me.austin.queer.nameable.hack

import me.austin.queer.manager.managers.HackManager
import me.austin.queer.misc.Globals
import me.austin.queer.util.file.FileHelper
import me.austin.queer.nameable.Nameable
import me.zero.alpine.listener.Listenable

import java.io.File

abstract class Hack(name: String, val category: Category) : Nameable(name), Listenable {
    val settings = mutableMapOf<String, Any>()
    val file: File
    protected var enabled: Boolean = false

    init {
        this.settings.put("enabled", enabled);

        file = File(HackManager.dir.toString() + "/" + this.name + ".json")
    }

    fun enable() {
        Globals.EVENTBUS.subscribe(this)

        this.enabled = true
    }

    fun disable() {
        Globals.EVENTBUS.unsubscribe(this)

        this.enabled = false
    }

    fun toggle() {
        if (this.enabled) disable() else enable()
    }

    open fun onEnable() {
    } 

    open fun onDisable() {
    }

    fun save() {
        try {
            FileHelper.createFile(file)
        }
        catch (e: Exception) {
            Globals.LOGGER.error("Couldn't save $name", this.name)
        }
    }
}

public enum class Category {
    MOVEMENT,
    COMBAT
}