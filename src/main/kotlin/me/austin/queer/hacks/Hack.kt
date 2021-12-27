package me.austin.queer.hacks

import me.austin.queer.feature.Nameable

abstract class Hack(name: String) : Nameable(name) {
    var enabled: Boolean = false
    val settings: HashMap<String, kotlin.Any> = HashMap()

    init {
        settings.put("enabled", enabled);
    }

    open fun onEnable() {
    }

    open fun onDisable() {
    }
}