package trans.rights.client.modules.hack.impl

import net.minecraft.entity.player.PlayerEntity
import trans.rights.client.events.TickEvent
import trans.rights.client.misc.api.Globals
import trans.rights.client.modules.hack.Hack
import trans.rights.client.modules.setting.impl.BooleanSetting
import trans.rights.client.modules.setting.impl.NumberSetting
import trans.rights.event.commons.EventHandler
import trans.rights.event.listener.impl.LambdaListener
import trans.rights.event.listener.impl.listener

object AutoHit : Hack("Auto-hit", "Automatically hit people near you"), Globals {
    private val waitForDelay =
        settings.add(BooleanSetting("Wait", "Wait until vanilla attack delay is over before attacking again?", true))
    private val customDelay =
        settings.add(NumberSetting("tick-delay", "How many ticks to wait until the next attack.", 20))

    private var ticks: Int = 0

    @EventHandler
    val updateListener: LambdaListener<TickEvent.PostTick> = listener { event ->
        if (!event.isInWorld) {
            if (waitForDelay.value && customDelay.value.toInt() != ticks) ticks++
            else if (getTarget() != null || waitForDelay.value && player.handSwinging) minecraft.interactionManager?.attackEntity(
                player,
                getTarget())
        }
    }

    private fun getTarget(): PlayerEntity? {
        if (minecraft.networkHandler!!.playerList.isEmpty()) return null

        return minecraft.world!!.players.stream().sorted(Comparator.comparingDouble { player ->
            minecraft.player!!.distanceTo(player).toDouble()
        }).findFirst().orElse(null)
    }
}
