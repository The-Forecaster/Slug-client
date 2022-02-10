package trans.rights.client.modules.hack.hacks

import trans.rights.client.events.TickEvent
import trans.rights.client.modules.hack.Hack
import trans.rights.event.listener.Listener
import trans.rights.event.listener.impl.LambdaListener

object AutoHit : Hack("Auto-hit", "Automatically hit people near you") {
    val updateListener: Listener<TickEvent.PostTick> = LambdaListener({

    }, -50,this, TickEvent.PostTick::class.java)
}
