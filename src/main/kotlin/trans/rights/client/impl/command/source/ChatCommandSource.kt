package trans.rights.client.impl.command.source

import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientCommandSource

class ChatCommandSource(minecraft: MinecraftClient) : ClientCommandSource(minecraft.networkHandler, minecraft)