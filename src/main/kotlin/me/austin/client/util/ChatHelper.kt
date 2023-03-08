package me.austin.client.util

import net.minecraft.text.Text
import me.austin.client.Slug.Companion.NAME
import me.austin.client.api.Wrapper

const val modifier = "§"

internal const val prefix = "${modifier}9[$NAME]${modifier}r "

fun Wrapper.clientSend(text: String, pre: Boolean = true) = minecraft.inGameHud.chatHud.addMessage(Text.of(if (pre)"$prefix $text" else text))

fun Wrapper.error(text: String, pre: Boolean = true) = minecraft.inGameHud.chatHud.addMessage(Text.of(if (pre) "$prefix${modifier}c $text" else "${modifier}c$text"))
