package me.austin.queer.manager.managers

import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import java.io.File
import me.austin.queer.Globals.EVENTBUS
import me.austin.queer.Globals
import me.austin.queer.TransRightsMod
import me.austin.queer.manager.Manager
import me.austin.queer.nameable.hack.Hack
import me.austin.queer.nameable.hack.hacks.*
import me.austin.queer.util.file.FileHelper
import me.zero.alpine.listener.Listenable
import me.zero.alpine.listener.Listener

object HackManager : Manager<Hack>(), Listenable {
    val dir = File(TransRightsMod.maindir.absolutePath + "/hacks")

    init {
        if (!dir.exists()) dir.mkdirs()

        this += FlightHack
        this += AuraHack

        this.values.forEach(Hack::load)

        EVENTBUS.subscribe(this)
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
