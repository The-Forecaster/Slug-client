package trans.rights.client.modules.hack

import java.io.File
import trans.rights.client.TransRights.Companion.maindir
import trans.rights.client.modules.Manager
import trans.rights.client.modules.hack.hacks.*

object HackManager : Manager<Hack>(mutableSetOf()) {
    val dir = File("$maindir/hacks")

    init {
        if (!dir.exists()) dir.mkdirs()
    }

    fun save() {
        this.values.stream().forEach { hack -> hack.save(hack.file) }
    }

    override fun load() {
        this.add(
            listOf(
                AutoHit,
                FlightHack,
                WallHack
            )
        )

        this.values.stream().forEach { hack ->
            hack.load(hack.file)
            hack.save(hack.file)
        }

        this.values.toSortedSet(Comparator.comparing(Hack::name))
    }

    override fun unload() {
        this.values.stream().forEach { hack ->
            hack.save(hack.file)
        }
        this.values.clear()
    }
}
