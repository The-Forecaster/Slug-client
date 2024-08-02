package me.austin.client.impl.hack

import me.austin.client.api.hack.Hack
import me.austin.client.impl.events.TickEvent
import me.austin.client.impl.friend.isFriend
import me.austin.client.impl.setting.BooleanSettingBuilder
import me.austin.client.impl.setting.IntSettingBuilder
import me.austin.client.impl.setting.Settings
import me.austin.rush.listener
import net.minecraft.entity.player.PlayerEntity

object AuraHack : Hack("Aura", "Automatically hit people near you") {
    private val customTick = IntSettingBuilder("tick-delay")
            .description("How many ticks to wait until the next attack.")
            .default(4)
            .minimum(0)
            .build()
    private val customDelay = BooleanSettingBuilder("Wait")
            .description("Wait until vanilla attack delay is over before attacking again?")
            .default(true)
            .children(customTick)
            .build()
    private val hitFriends = BooleanSettingBuilder("Friends")
            .description("Whether to attack friends or not")
            .default(false)
            .build()

    override val settings = Settings(customDelay, hitFriends)

    private var ticks = 0

    override val listeners = listOf(listener<TickEvent> { event ->
        if (event.isInWorld && getTarget() != null) {
            if (!customDelay.value and !player!!.handSwinging) {
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

        // Best way of doing this even though it's kinda stupid
        return minecraft.world!!.players.stream().sorted(Comparator.comparingDouble {
            minecraft.player!!.distanceTo(it).toDouble()
        }).filter { !player!!.isFriend or !hitFriends.value }.findFirst().orElse(null)
    }
}
