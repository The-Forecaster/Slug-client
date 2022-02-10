package trans.rights.client.modules.hack

import java.io.File
import trans.rights.client.modules.Manager
import trans.rights.client.modules.hack.hacks.*
import trans.rights.client.util.file.maindir

object HackManager : Manager<Hack>(mutableSetOf()) {
    val dir = File("$maindir/hacks")

    init {
        if (!dir.exists()) dir.mkdirs()

        this.add(FlightHack)
        this.add(AutoHit)
    }

    fun save() {
        this.values.forEach { hack -> hack.save(hack.file) }
    }

    override fun load() {
        this.values.forEach { hack ->
            hack.load(hack.file)
            hack.save(hack.file)
        }
    }

    override fun unload() {
        this.values.forEach { hack ->
            hack.save(hack.file)
            hack.settings.clear()
        }
        values.clear()
    }
}
