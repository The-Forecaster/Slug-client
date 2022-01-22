package me.austin.queer.modules

import me.austin.queer.modules.command.CommandManager
import me.austin.queer.modules.hack.HackManager

abstract class Manager<T : Module>(val values: MutableList<T> = mutableListOf()) : Module("", "") {
    companion object : Manager<Manager<*>>() {
        override fun load() {
            this.add(HackManager).also { HackManager.save() }
            this.add(CommandManager)
        }
    }

    abstract fun load()

    default fun add(value: T): T {
        this.values.add(value)

        return value
    }

    default open fun unload() {
        this.values.clear()
    }
}
