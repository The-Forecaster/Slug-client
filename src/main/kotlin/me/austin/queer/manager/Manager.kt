package me.austin.queer.manager

import me.austin.queer.manager.managers.CommandManager
import me.austin.queer.manager.managers.HackManager
import me.austin.queer.nameable.Nameable

abstract class Manager<T : Nameable>(val values : MutableList<T> = mutableListOf()) {
    companion object {
        @JvmStatic
        private val managers = mutableListOf<Manager<*>>()

        @JvmStatic
        fun loadManagers() : List<Manager<*>> {
            managers.add(HackManager)
            managers.add(CommandManager)

            return managers
        }

        @JvmStatic
        fun unloadManagers() : List<Manager<*>> {
            managers.clear()

            return managers
        }
    }

    operator fun plus(value : T): Manager<T> {
        this += value

        return this
    }

    operator fun plusAssign(value : T) {
        this.values.add(value)
    }
}