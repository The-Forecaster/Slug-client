package me.austin.queer.nameable.hack

import me.austin.queer.misc.Globals
import me.austin.queer.nameable.Nameable
import me.zero.alpine.listener.Listenable

abstract class Hack(name: String, val category: Category) : Nameable(name), Listenable {
    
    public val settings: HashMap<String, Any> = HashMap()
    private var enabled: Boolean = false

    init {
        this.settings.put("enabled", enabled);
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
        if (this.enabled) disable()
        else enable()
    }

    open fun onEnable() {
        Globals.LOGGER.info(this.name + " enabled")
    } 

    open fun onDisable() {
        Globals.LOGGER.info(this.name + " disabled")
    }
}

public enum class Category {
    MOVEMENT
}