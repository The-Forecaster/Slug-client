package trans.rights.client.api.hack

import trans.rights.TransRights.Companion.mainDirectory
import trans.rights.client.api.commons.Manager
import trans.rights.client.impl.hack.AutoHit
import trans.rights.client.impl.hack.FlightHack
import trans.rights.client.impl.hack.WallHack
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

object HackManager : Manager<Hack>(linkedSetOf(AutoHit, FlightHack, WallHack)) {
    val directory: Path = Paths.get("$mainDirectory/hacks")

    fun save() {
        values.stream().forEach { hack -> hack.save(hack.file) }
    }

    override fun load() {
        if (!Files.exists(directory)) Files.createDirectory(directory)

        values.stream().forEach { hack -> hack.load(hack.file) }

        values.sortedWith(Comparator.comparing(Hack::name))
    }

    override fun unload() {
        save()
    }
}
