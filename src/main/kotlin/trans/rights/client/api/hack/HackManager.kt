package trans.rights.client.api.hack

import trans.rights.TransRights.Companion.mainDirectory
import trans.rights.client.api.commons.Manager
import trans.rights.client.impl.hack.AutoHit
import trans.rights.client.impl.hack.FlightHack
import trans.rights.client.impl.hack.WallHack
import java.nio.file.Files
import java.nio.file.Path

object HackManager : Manager<Hack>(linkedSetOf()) {
    val directory: Path = Path.of("$mainDirectory/hacks")

    fun save() = values.stream().forEach(Hack::save)

    override fun load() {
        if (!Files.exists(directory)) Files.createDirectory(directory)

        values.addAll(listOf(AutoHit, FlightHack, WallHack))

        values.stream().forEach(Hack::load)

        values.sortedWith(Comparator.comparing(Hack::name))
    }

    override fun unload() {
        save()

        super.unload()
    }
}
