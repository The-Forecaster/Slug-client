package me.austin.queer.modules.command.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType.*
import me.austin.queer.modules.hack.HackManager
import me.austin.queer.modules.command.Command
import me.austin.queer.modules.hack.Hack
import net.minecraft.server.command.CommandManager.*
import net.minecraft.server.command.ServerCommandSource

object HackCommand : Command("hack-command", "Change the settings of a Hack") {
    override fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            literal("")
            .then(argument("hackname", string()))
            .executes({ ctx -> toggleHack(getHack(ctx.toString())) })
            .then(argument("settingname", string()))
            .then(argument("value", string()))
            .executes({ ctx ->
                takeInput(
                    ctx.getArgument("value", string().javaClass).toString(),
                    getSetting(
                        ctx.getArgument("settingname", string().javaClass)
                            .toString(),
                        getHack(
                            ctx.getArgument("hackname", string().javaClass)
                                .toString()
                        )
                    ),
                    getHack(
                        ctx.getArgument("hackname", string().javaClass)
                            .toString()
                    )
                )
            })
        )
    }

    private fun getHack(name: String): Hack {
        for (hack in HackManager.values) {
            if (hack.name.lowercase().equals(name.lowercase())) return hack
        }

        throw builtins.dispatcherUnknownArgument().create()
    }

    private fun getSetting(name: String, hack: Hack): String {
        for (setting in hack.settings) {
            if (setting.key.lowercase().equals(name.lowercase())) return setting.key
        }

        throw builtins.dispatcherUnknownArgument().create()
    }

    fun toggleHack(hack: Hack): Int {
        hack.toggle()
        return 0
    }

    fun takeInput(input: String, key: String, hack: Hack): Int {
        try {
            hack.settings.set(key, input)
        } 
        catch (e: Exception) {
            throw builtins.dispatcherUnknownArgument().create()
        }

        return 0
    }
}
