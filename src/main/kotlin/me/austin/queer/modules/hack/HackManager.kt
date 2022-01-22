package me.austin.queer.modules.hack

import java.io.File
import me.austin.queer.modules.Manager
import me.austin.queer.modules.hack.hacks.*
import me.austin.queer.util.file.maindir

object HackManager : Manager<Hack>() {
    val dir = File(maindir.toString() + "/hacks")

    init {
        if (!dir.exists()) dir.mkdirs()

        this.add(FlightHack)
        this.add(AutoHit)
    }

    fun save() {
        this.values.forEach(Hack::save)
    }

    override fun load() {
        this.values.forEach { hack ->
            hack.load()
            hack.save()
        }
    }

    override fun unload() {
        this.values.forEach { hack ->
            hack.save()
            hack.settings.clear()
        }
        values.clear()
    }
}
