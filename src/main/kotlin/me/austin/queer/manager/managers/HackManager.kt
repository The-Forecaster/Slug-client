package me.austin.queer.manager.managers

import me.austin.queer.TransRightsMod
import me.austin.queer.manager.Manager
import me.austin.queer.util.file.FileHelper
import me.austin.queer.nameable.hack.Hack
import me.austin.queer.nameable.hack.hacks.*

import java.io.File
import java.nio.file.Path
import com.google.gson.*

object HackManager: Manager<Hack>() {
    val dir = File(TransRightsMod.maindir.absolutePath + "hacks")

    init {
        if (!dir.exists()) dir.mkdirs();

        this += Flight
        this += AutoHit
    }

    override fun save() {
        for (hack in this.values) {
            val obj = JsonObject()
            val hackfile = File(dir.absolutePath + '/' + hack.name)

            for (setting in hack.settings) {
                obj.add(setting.key, JsonPrimitive(setting.value.toString()))
            }
            FileHelper.writeToJson(obj, hackfile.toPath())
        }
    }

    override fun unload() {
        for (hack in this.values) {
            hack.settings.clear()
        }
        values.clear()
    }
}