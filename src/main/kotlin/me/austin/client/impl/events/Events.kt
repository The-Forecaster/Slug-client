package me.austin.client.impl.events

import me.austin.rush.Cancellable

data class KeyEvent(val key: Int) : Cancellable()