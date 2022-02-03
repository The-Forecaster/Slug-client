package trans.rights.event.bus.impl

import trans.rights.event.annotation.EventHandler
import trans.rights.event.bus.EventBus
import trans.rights.event.listener.Listener
import trans.rights.event.listener.impl.MethodListener
import trans.rights.event.type.ICancellable
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArraySet

class EventManager(private val registeredListeners: MutableSet<Any?> = mutableSetOf(), override val subscribers: ConcurrentHashMap<Class<*>, CopyOnWriteArraySet<MethodListener<*>>> = ConcurrentHashMap()) : EventBus {
    override fun register(subscriber: Any) {
        if (this.registeredListeners.contains(subscriber))
            return

        Arrays.stream(subscriber.javaClass.methods).filter {
                method -> method.isAnnotationPresent(EventHandler::class.java) && method.parameterCount == 1
        }.forEach { method ->
            subscribers.getOrPut(
                method.parameters[0].javaClass,
                ::CopyOnWriteArraySet).
            add(MethodListener(
                method,
                method.getAnnotation(EventHandler::class.java).priority,
                subscriber,
                method.parameters[0].javaClass)
            )
        }

        this.registeredListeners.add(subscribers)
    }

    override fun unregister(subscriber: Any) {
        if (!this.registeredListeners.contains(subscriber))
            return

        Arrays.stream(subscriber.javaClass.methods).filter {
                method -> method.isAnnotationPresent(EventHandler::class.java) && method.parameterCount == 1
        }.forEach { method ->
            subscribers[method.parameters[0].javaClass]?.forEach { listener ->
                if (listener.action == method) subscribers[method.parameters[0].javaClass]?.remove(listener)
            }
        }

        this.registeredListeners.remove(subscriber)
    }

    override fun isRegistered(subscriber: Any): Boolean {
        return this.registeredListeners.contains(subscriber)
    }

    override fun <T> dispatch(event: T): T {
        if (this.subscribers[event!!::class.java] == null) {
            return event
        }
        else if (this.subscribers[event!!::class.java]?.size == 0) {
            return event
        }

        val listeners = this.subscribers[event!!::class.java] as MutableList<Listener<T>>

        listeners.forEach { listener ->
            listener.invoke(event)
        }

        return event
    }

    override fun <T : ICancellable> dispatch(event: T): T {
        val listeners = this.subscribers[event!!::class.java] ?: return event

        return event
    }
}