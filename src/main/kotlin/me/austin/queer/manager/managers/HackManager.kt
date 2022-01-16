package me.austin.queer.manager.managers

import java.io.File
import me.austin.queer.Globals.EVENTBUS
import me.austin.queer.TransRights
import me.austin.queer.manager.Manager
import me.austin.queer.nameable.hack.Hack
import me.austin.queer.nameable.hack.hacks.*
import me.austin.queer.util.file.maindir
import me.zero.alpine.listener.Listenable

object HackManager : Manager<Hack>() {
    val dir = File(maindir.toString() + "/hacks")

    init {
        if (!dir.exists()) dir.mkdirs()

        this.values.add(FlightHack).also { FlightHack.load()}
        this.values.add(AutoHit).also { AutoHit.load() }
    }

    fun save() { 
        this.values.forEach(Hack::save) 
    }

    fun unload() {
        for (hack in this.values) {
            hack.settings.clear()
        }
        values.clear()
    }
}
