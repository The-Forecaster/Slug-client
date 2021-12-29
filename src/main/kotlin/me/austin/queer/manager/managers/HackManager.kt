package me.austin.queer.manager.managers

import me.austin.queer.manager.Manager
import me.austin.queer.nameable.hack.Hack
import me.austin.queer.nameable.hack.hacks.Flight

object HackManager: Manager<Hack>() {
    init {
        values.add(Flight)
    }
}