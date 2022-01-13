package me.austin.queer.manager.managers

import java.io.File
import me.austin.queer.Globals.EVENTBUS
import me.austin.queer.TransRights
import me.austin.queer.manager.Manager
import me.austin.queer.nameable.hack.Hack
import me.austin.queer.nameable.hack.hacks.*
import me.zero.alpine.listener.Listenable

object HackManager : Manager<Hack>(), Listenable {
    val dir = File(TransRights.maindir.absolutePath + "/hacks")

    init {
        if (!dir.exists()) dir.mkdirs()

        this += FlightHack
        this += AutoHit

        this.values.forEach(Hack::load)

        EVENTBUS.subscribe(this)
    }

    fun save() {
        Thread({ this.values.forEach(Hack::save) })
    }

    fun unload() {
        for (hack in this.values) {
            hack.settings.clear()
        }
        values.clear()
    }
}
