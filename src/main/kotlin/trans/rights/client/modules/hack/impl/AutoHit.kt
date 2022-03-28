package trans.rights.client.modules.hack.impl

import trans.rights.client.events.TickEvent
import trans.rights.client.modules.hack.Hack
import trans.rights.client.modules.setting.settings.BooleanSetting
import trans.rights.client.modules.setting.settings.NumberSetting
import trans.rights.event.annotation.EventHandler
import trans.rights.event.listener.impl.*

object AutoHit : Hack("Auto-hit", "Automatically hit people near you") {
    private val waitForDelay = BooleanSetting("Wait", true)
    private val swap = BooleanSetting("Swap", true)
    private val delay = NumberSetting("tick-delay", 6)

    @EventHandler
    var updateListener: LambdaListener<TickEvent.PostTick> = lambdaListener { event -> 
        if (nullCheck() || !event.isInWorld) disable() 
    }

    init {
        settings.add(waitForDelay)
        settings.add(swap)
        settings.add(delay)
    }
}
