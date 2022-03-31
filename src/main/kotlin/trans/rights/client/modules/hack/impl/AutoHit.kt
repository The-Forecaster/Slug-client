package trans.rights.client.modules.hack.impl

import trans.rights.client.events.TickEvent
import trans.rights.client.misc.api.Globals
import trans.rights.client.modules.hack.Hack
import trans.rights.client.modules.setting.impl.BooleanSetting
import trans.rights.client.modules.setting.impl.NumberSetting
import trans.rights.event.annotation.EventHandler
import trans.rights.event.listener.impl.lambdaListener
import trans.rights.event.listener.impl.LambdaListener

object AutoHit : Hack("Auto-hit", "Automatically hit people near you"), Globals {
    private val waitForDelay = settings.add(BooleanSetting("Wait", true))
    private val swap = settings.add(BooleanSetting("Swap", true))
    private val delay = settings.add(NumberSetting("tick-delay", 6))

    @EventHandler
    val updateListener: LambdaListener<TickEvent.PostTick> = lambdaListener { event ->
        if (nullCheck() || !event.isInWorld) disable()
    }
}
