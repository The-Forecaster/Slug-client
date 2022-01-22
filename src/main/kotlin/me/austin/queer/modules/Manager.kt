package me.austin.queer.modules

import me.austin.queer.modules.command.CommandManager
import me.austin.queer.modules.hack.HackManager

import me.austin.queer.Globals.LOGGER
import me.austin.queer.TransRights.Companion.NAME

abstract class Manager<T : Module>(val values: MutableList<T> = mutableListOf()) : Module("", "") {
    companion object : Manager<Manager<*>>() {
        fun reload() {
            this.load()
            this.unload()

            LOGGER.info("$NAME has reloaded")
        }

        override fun load() {
            this.add(HackManager)
            this.add(CommandManager)
        }

        override fun unload() {
            this.values.clear()
        }
    }

    abstract fun load()

    fun add(value: T): T {
        this.values.add(value)

        return value
    }

    open fun unload() {
        this.values.clear()
    }
}
