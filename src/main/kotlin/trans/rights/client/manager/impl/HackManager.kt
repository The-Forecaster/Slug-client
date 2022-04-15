package trans.rights.client.manager.impl

import trans.rights.TransRights.Companion.mainDirectory
import trans.rights.client.manager.Manager
import trans.rights.client.modules.hack.Hack
import trans.rights.client.modules.hack.impl.AutoHit
import trans.rights.client.modules.hack.impl.FlightHack
import trans.rights.client.modules.hack.impl.WallHack
import java.io.File
import java.nio.file.Files

object HackManager : Manager<Hack>(mutableSetOf()) {
    val directory = File("$mainDirectory/hacks")

    fun save() {
        values.stream().forEach { hack -> hack.save(hack.file) }
    }

    override fun invoke(): HackManager {
        add(AutoHit, FlightHack, WallHack)

        if (!directory.exists()) Files.createDirectory(directory.toPath())

        this.values.stream().forEach { hack -> hack.load(hack.file) }

        save()

        values.sortedWith(Comparator.comparing(Hack::name))

        return this
    }

    override fun unload() {
        save()

        values.clear()
    }
}
