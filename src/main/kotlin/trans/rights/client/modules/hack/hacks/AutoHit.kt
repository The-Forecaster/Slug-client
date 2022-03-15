package trans.rights.client.modules.hack.hacks

import trans.rights.client.events.TickEvent
import trans.rights.client.modules.hack.Hack
import trans.rights.client.modules.setting.settings.BooleanSetting
import trans.rights.client.modules.setting.settings.IntSetting
import trans.rights.event.annotation.EventHandler

object AutoHit : Hack("Auto-hit", "Automatically hit people near you") {
    private val waitForDelay = BooleanSetting("Wait", "If you want the client to spam click or wait for the attack delay", true)
    private val swap = BooleanSetting("Swap", "If you want the client to swap to the highest damage weapon in your hotbar", true)
    private val delay = IntSetting("tick-delay", "how long, in ticks, the client should wait before the next hit", 6)

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
