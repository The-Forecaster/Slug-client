package trans.rights.event.bus.impl

import trans.rights.event.bus.EventBus
import trans.rights.event.listener.Listener
import trans.rights.event.type.ICancellable
import java.util.concurrent.ConcurrentHashMap

class EventManager : EventBus {
    override val subscribers: MutableMap<Class<*>, MutableSet<Listener<*>>> = ConcurrentHashMap()

    private val registeredListeners: MutableSet<Any?> = mutableSetOf()

    override fun register(subscriber: Any?) {
        if (registeredListeners.contains(subscriber))
            return
    }

    override fun unregister(subscriber: Any?) {
    }

    override fun isRegistered(subscriber: Any?): Boolean {
        return registeredListeners.contains(subscriber)
    }

    override fun <T> dispatch(event: T): T {
        if (subscribers[event!!::class.java] == null) {
            return event
        }
        else if (subscribers[event!!::class.java]?.size == 0) {
            return event
        }

        val listeners = subscribers[event!!::class.java] as MutableList<Listener<T>>

        listeners.forEach { listener ->
            listener.invoke(event)
        }

        return event
    }

    override fun <T : ICancellable?> dispatch(event: T): T {
        val subscribers = subscribers[event!!::class.java] ?: return event

        return event
    }
}