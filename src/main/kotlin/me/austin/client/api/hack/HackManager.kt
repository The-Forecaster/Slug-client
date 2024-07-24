package me.austin.client.api.hack

import me.austin.client.Slug.Companion.mainDirectory
import me.austin.client.api.Manager
import me.austin.client.api.command.CommandManager
import me.austin.client.impl.hack.*
import java.nio.file.Files
import java.nio.file.Path

object HackManager : Manager<Hack, List<Hack>> {
    internal val directory: Path = Path.of("$mainDirectory/hacks")

    override val values = listOf(AntiFabric, AntiKick, AuraHack, FlightHack, WallHack)

    override fun load() {
        if (Files.notExists(directory)) Files.createDirectory(directory)

        for (hack in values) {
            hack.load()

            hack.register(CommandManager.dispatcher)
        }
    }

    override fun unload() {
        for (hack in values) {
            hack.unload()
        }
    }
}
