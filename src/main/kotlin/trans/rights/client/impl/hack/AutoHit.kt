package trans.rights.client.impl.hack

import net.minecraft.entity.player.PlayerEntity
import trans.rights.client.api.friend.isFriend
import trans.rights.client.api.hack.Hack
import trans.rights.client.events.TickEvent
import trans.rights.client.impl.setting.BooleanSetting
import trans.rights.client.impl.setting.NumberSetting
import trans.rights.event.type.EventHandler
import trans.rights.event.listener.impl.LambdaListener
import trans.rights.event.listener.impl.listener
import kotlin.Comparator

object AutoHit : Hack("Aura", "Automatically hit people near you") {
    private val waitForDelay = settings.add(BooleanSetting("Wait", "Wait until vanilla attack delay is over before attacking again?", true))
    private val customDelay = settings.add(NumberSetting("tick-delay", "How many ticks to wait until the next attack.", 20))
    private val hitFriends = settings.add(BooleanSetting("Friends", "Whether to attack friends or not", false))

    private var ticks: Int = 0

    @EventHandler
    val updateListener: LambdaListener<TickEvent.PostTick> = listener { event ->
        if (event.isInWorld && getTarget() != null) {
            if ((waitForDelay.value && !player.handSwinging) || customDelay.value == ticks.toDouble()) minecraft.interactionManager?.attackEntity(
                player,
                getTarget()
            )

            else if (customDelay.value == ticks.toDouble()) minecraft.interactionManager?.attackEntity(
                player,
                getTarget()
            )

            else ticks ++
        }
    }

    private fun getTarget(): PlayerEntity? {
        if (minecraft.networkHandler!!.playerList.isEmpty()) return null

        return minecraft.world!!.players.stream().sorted(Comparator.comparingDouble { player ->
            minecraft.player!!.distanceTo(player).toDouble()
        }).filter { !player.isFriend() || !hitFriends.value}.findFirst().orElse(null)
    }
}
