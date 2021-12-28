package me.austin.queer.hacks

import me.austin.queer.feature.Nameable
import me.austin.queer.misc.Globals
import me.zero.alpine.listener.Listenable

abstract class Hack(name: String, val category: Category) : Nameable(name), Listenable {
    var enabled: Boolean = false
    val settings: HashMap<String, Any> = HashMap()

    init {
        settings.put("enabled", enabled);
    }

    open fun onEnable() {
        Globals.EVENTBUS.subscribe(this)
    } 

    open fun onDisable() {
        Globals.EVENTBUS.unsubscribe(this)
    }
}

public enum class Category {
    MOVEMENT
}