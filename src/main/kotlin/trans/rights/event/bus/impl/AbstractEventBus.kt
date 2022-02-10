package trans.rights.event.bus.impl

import trans.rights.event.bus.EventBus
import trans.rights.event.listener.Listener
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArraySet

abstract class AbstractEventBus : EventBus {
    override val subscribers: ConcurrentHashMap<Class<*>, CopyOnWriteArraySet<Listener<*>>> = ConcurrentHashMap()

    private val registeredListeners: MutableSet<Any?> = CopyOnWriteArraySet()

    /**
     * Finds and registers all valid listener fields in a target object class
     */
    abstract fun registerFields(subscriber: Any)

    /**
     * Finds and registers all valid methods in a target object class
     */
    abstract fun registerMethods(subscriber: Any)

    /**
     * Finds and removes all valid fields from the subscriber registry
     */
    abstract fun unregisterFields(subscriber: Any)

    /**
     * Finds and removes all valid methods from the subscriber registry
     */
    abstract fun unregisterMethods(subscriber: Any)

    override fun register(subscriber: Any) {
        if (isRegistered(subscriber)) return

        this.registerFields(subscriber).also { this.registerMethods(subscriber) }

        this.registeredListeners.add(subscribers)

        this.subscribers.values.stream().forEach { listeners ->
            listeners.toSortedSet(Comparator.comparing { listener -> listener })
        }
    }

    override fun unregister(subscriber: Any) {
        if (!isRegistered(subscriber)) return

        this.unregisterFields(subscriber).also { this.unregisterMethods(subscriber) }

        this.registeredListeners.remove(subscriber)
    }

    override fun isRegistered(subscriber: Any): Boolean {
        return this.registeredListeners.contains(subscriber)
    }

    override fun <T: Any> dispatch(event: T): T {
        if (this.subscribers[event::class.java]?.size != 0) {
            getOrPutList(event.javaClass).stream().forEach { listener ->
                listener.invoke(event)
            }
        }

        return event
    }

    protected fun <T> getOrPutList(clazz: Class<T>): CopyOnWriteArraySet<Listener<T>> {
        return this.subscribers.getOrPut(clazz, ::CopyOnWriteArraySet) as CopyOnWriteArraySet<Listener<T>>
    }
}