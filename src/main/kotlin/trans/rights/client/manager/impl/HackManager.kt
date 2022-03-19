package trans.rights.client.manager.impl

import java.io.File
import java.nio.file.Files
import trans.rights.client.TransRights.Companion.mainDirectory
import trans.rights.client.manager.Manager
import trans.rights.client.modules.hack.Hack
import trans.rights.client.modules.hack.impl.*

object HackManager : Manager<Hack>(mutableSetOf()) {
    val directory = File("$mainDirectory/hacks")

    init {
        if (!directory.exists()) Files.createDirectory(directory.toPath())
    }

    fun save() {
        this.values.stream().forEach { hack -> hack.save(hack.file) }
    }

    override fun load() {
        this.add(listOf(AutoHit, FlightHack, WallHack))

        this.values.stream().forEach { hack ->
            hack.load(hack.file)
            hack.save(hack.file)
        }

        this.values.toSortedSet(Comparator.comparing(Hack::name))
    }

    override fun unload() {
        this.values.stream().forEach { hack -> hack.save(hack.file) }
        this.values.clear()
    }
}
