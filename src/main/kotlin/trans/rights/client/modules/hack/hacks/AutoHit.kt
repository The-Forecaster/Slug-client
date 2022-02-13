package trans.rights.client.modules.hack.hacks

import trans.rights.client.events.TickEvent
import trans.rights.client.modules.hack.Hack
import trans.rights.event.annotation.EventHandler
import com.mojang.datafixers.FunctionType
import com.mojang.datafixers.DataFix

object AutoHit : Hack("Auto-hit", "Automatically hit people near you") {

    private var waitForDelay: Boolean = true

    private var tickDelay: Double = 3.0

    private var swap: Boolean = true

    init {
        this.settings["wait"] = waitForDelay
        this.settings["delay"] = tickDelay
        this.settings["swap"] = swap
    }

    @EventHandler
    fun onUpdate(event: TickEvent.PostTick) {
        if (nullCheck() || !event.isInWorld) return

        val player = this.minecraft.player

        if (this.swap) {
            
        }
    }
}
