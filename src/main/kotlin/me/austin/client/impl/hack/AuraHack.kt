package me.austin.client.impl.hack

import net.minecraft.entity.player.PlayerEntity
import me.austin.client.api.hack.Hack
import me.austin.client.events.TickEvent
import me.austin.client.impl.friend.isFriend
import me.austin.client.impl.setting.BooleanSetting
import me.austin.client.impl.setting.IntSetting
import me.austin.client.impl.setting.Settings
import me.austin.rush.listener

object AuraHack : Hack("Aura", "Automatically hit people near you") {
    private val customTick = IntSetting("tick-delay", "How many ticks to wait until the next attack.", 4)
    private val customDelay = BooleanSetting("Wait", "Wait until vanilla attack delay is over before attacking again?", true, customTick)
    private val hitFriends = BooleanSetting("Friends", "Whether to attack friends or not", false)

    override val settings = Settings(customDelay, hitFriends)

    private var ticks = 0

    override val listeners = listOf(listener<TickEvent.Post> { event ->
        if (event.isInWorld && getTarget() != null) {
            if (!customDelay.value && !player!!.handSwinging) {
                minecraft.interactionManager?.attackEntity(player, getTarget())
            } else if (customDelay.value) {
                if (customTick.value == ticks) {
                    minecraft.interactionManager?.attackEntity(player, getTarget())

                    ticks = 0
                } else ticks++
            }
        }
    })

    private fun getTarget(): PlayerEntity? {
        if (minecraft.networkHandler!!.playerList.isEmpty()) return null

        return minecraft.world!!.players.stream().sorted(Comparator.comparingDouble { player ->
            minecraft.player!!.distanceTo(player).toDouble()
        }).filter { !player!!.isFriend || !hitFriends.value }.findFirst().orElse(null)
    }
}
