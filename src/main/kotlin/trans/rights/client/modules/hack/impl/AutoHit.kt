package trans.rights.client.modules.hack.impl

import net.minecraft.entity.player.PlayerEntity
import trans.rights.client.events.TickEvent
import trans.rights.client.misc.api.Globals
import trans.rights.client.modules.hack.Hack
import trans.rights.client.modules.setting.impl.BooleanSetting
import trans.rights.client.modules.setting.impl.NumberSetting
import trans.rights.event.annotation.EventHandler
import trans.rights.event.listener.impl.LambdaListener
import trans.rights.event.listener.impl.lambdaListener

object AutoHit : Hack("Auto-hit", "Automatically hit people near you"), Globals {
    private val waitForDelay = settings.add(BooleanSetting("Wait", true))
    private val delay = settings.add(NumberSetting("tick-delay", 20))

    private var ticks: Int = 0

    @EventHandler
    val updateListener: LambdaListener<TickEvent.PostTick> = lambdaListener { event ->
        if (!event.isInWorld) {
            if (!waitForDelay.value || delay.value.toInt() != ticks) {
                if (getTarget() != null) minecraft.interactionManager!!.attackEntity(player, getTarget())

                ticks = 0
            }
            else ticks ++
        }
    }

    private fun getTarget(): PlayerEntity? {
        if (minecraft.networkHandler!!.playerList.isEmpty()) return null

        return minecraft.world!!.players.stream().min(Comparator.comparingDouble { player ->
            minecraft.player!!.distanceTo(player).toDouble()
        }).get()
    }
}
