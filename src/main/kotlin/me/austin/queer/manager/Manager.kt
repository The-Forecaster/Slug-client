package me.austin.queer.manager

import me.austin.queer.manager.managers.CommandManager
import me.austin.queer.manager.managers.HackManager

abstract class Manager<T>() {
    companion object {
        @JvmStatic
        fun loadManagers() {
            HackManager
            CommandManager
        }
    }

    var values = ArrayList<T>()

    fun add(value: T) {
        values.add(value)
    }    
}