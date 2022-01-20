package me.austin.queer.manager

import me.austin.queer.manager.managers.CommandManager
import me.austin.queer.manager.managers.HackManager
import me.austin.queer.nameable.Nameable

abstract class Manager<T : Nameable>(val values: MutableList<T> = mutableListOf()) {
    companion object {
        private val managers = mutableListOf<Manager<*>>()

        fun loadManagers(): List<Manager<*>> {
            managers.add(HackManager).also { HackManager.save() }
            managers.add(CommandManager)

            return managers
        }

        fun unloadManagers(): List<Manager<*>> {
            managers.clear()

            return managers
        }
    }

    fun add(value: T): T {
        this.values.add(value)

        return value
    }
}
