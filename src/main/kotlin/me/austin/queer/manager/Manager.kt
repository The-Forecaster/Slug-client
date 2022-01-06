package me.austin.queer.manager

import me.austin.queer.manager.managers.CommandManager
import me.austin.queer.manager.managers.HackManager
import me.austin.queer.nameable.Nameable

abstract class Manager<T : Nameable> {
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
        fun add(manager: Manager<*>) {
            managers.add(manager)
        }
        
        @JvmStatic
        fun saveManagers() {
            managers.forEach({it.save()})
        }
        
        @JvmStatic
        fun unloadManagers() {
            managers.forEach({it.unload()})
            managers.clear()
        }
    }

    val values = mutableListOf<T>()

    
    abstract fun save()
    abstract fun unload()

    operator fun plus(value: T): Manager<T> {
        this += value

        return this
    }

    operator fun plusAssign(value : T) {
        this.values.add(value)
    }
}