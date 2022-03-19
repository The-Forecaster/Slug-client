package trans.rights.client.modules.hack.impl

import trans.rights.client.events.TickEvent
import trans.rights.client.modules.hack.Hack
import trans.rights.client.modules.setting.settings.BooleanSetting
import trans.rights.client.modules.setting.settings.IntSetting
import trans.rights.event.annotation.EventHandler

object AutoHit : Hack("Auto-hit", "Automatically hit people near you") {
    private val waitForDelay = BooleanSetting("Wait", true)
    private val swap = BooleanSetting("Swap", true)
    private val delay = IntSetting("tick-delay", 6)

    init {
        settings.add(waitForDelay)
        settings.add(swap)
        settings.add(delay)
    }

    @EventHandler
    fun onUpdate(event: TickEvent.PostTick) {
        if (nullCheck() || !event.isInWorld) {
            disable()
        }
    }
}
