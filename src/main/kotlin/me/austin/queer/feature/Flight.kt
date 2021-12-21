package me.austin.queer.hacks

import me.zero.alpine.listener.EventHandler
import me.zero.alpine.listener.EventHook
import me.zero.alpine.listener.Listener

import me.austin.queer.feature.Feature
import me.austin.queer.util.Globals
import me.austin.queer.events.PacketEvent
import me.austin.queer.events.TickEvent
import net.minecraft.entity.player.PlayerAbilities
import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket

object Flight : Feature("Flight"), Globals {
    val mode : FlightMode = FlightMode.CREATIVE
    val speed: Float = 10.0f

    @EventHandler
    val packetlistener = Listener<PacketEvent.Recieve>({
        if (it.getPacket() is PlayerAbilitiesS2CPacket) return@Listener

        when (mode) {
            FlightMode.CREATIVE -> {
                mc.send({
                    var abilities : PlayerAbilities = PlayerAbilities()
                    abilities.creativeMode = true

                    mc.!!networkHandler.sendPacket(PlayerAbilitiesS2CPacket(PlayerAbilities()))
                })
            }
            FlightMode.SPECTATOR -> {
                
            }
            FlightMode.PACKET -> {
                
            }
        }
    })

    @EventHandler
    val updateListener = Listener<TickEvent.ClientTick>({
        when (mode) {
            FlightMode.CREATIVE -> {

            }
            FlightMode.SPECTATOR -> {
                
            }
            FlightMode.PACKET -> {
                
            }
        }
    })

    enum class FlightMode {
        CREATIVE,
        SPECTATOR,
        PACKET
    }
}
