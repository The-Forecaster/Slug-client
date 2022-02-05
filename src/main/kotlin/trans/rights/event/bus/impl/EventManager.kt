package trans.rights.event.bus.impl

import java.lang.reflect.Field
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArraySet
import trans.rights.event.annotation.EventHandler
import trans.rights.event.annotation.EventListener
import trans.rights.event.bus.EventBus
import trans.rights.event.listener.impl.MethodListener
import trans.rights.event.listener.impl.LambdaListener
import trans.rights.event.listener.Listener
import trans.rights.event.type.ICancellable
import java.lang.reflect.Method

class EventManager(
    override val subscribers: ConcurrentHashMap<Class<*>, CopyOnWriteArraySet<Listener<*>>> = ConcurrentHashMap(),
    private val registeredListeners: MutableSet<Any?> = mutableSetOf()
) : EventBus {

    override fun register(subscriber: Any) {
        if (this.registeredListeners.contains(subscriber)) return

        Arrays.stream(subscriber.javaClass.methods).filter { method ->
            method.isAnnotationPresent(EventHandler::class.java) && method.parameterCount == 1
        }
        .forEach { method -> 
            subscribers.getOrPut(method.parameters[0].javaClass, ::CopyOnWriteArraySet).add(
                asListener(subscriber, method, method.getAnnotation(EventHandler::class.java).priority)
            )
        }

        Arrays.stream(subscriber.javaClass.declaredFields).filter { field ->
            field.isAnnotationPresent(EventListener::class.java) && field.get(subscriber) is Listener<*>
        }
        .forEach { field ->
            val list = asListener(subscriber, field)

            subscribers.getOrPut(list.target, ::CopyOnWriteArraySet).add(list)
        }

        this.registeredListeners.add(subscribers)
    }

    override fun unregister(subscriber: Any) {
        if (!this.registeredListeners.contains(subscriber)) return

        Arrays.stream(subscriber.javaClass.methods).filter { method ->
            method.isAnnotationPresent(EventHandler::class.java) && method.parameterCount == 1
        }
        .forEach { method ->
            subscribers[method.parameters[0].javaClass]?.forEach { listener ->
                subscribers[method.parameters[0].javaClass]?.remove(listener)
            }
        }
        this.registeredListeners.remove(subscriber)
    }

    override fun isRegistered(subscriber: Any): Boolean {
        return this.registeredListeners.contains(subscriber)
    }

    override fun <T: Any> dispatch(event: T): T {
        if (this.subscribers[event::class.java]?.size != 0) {
            (subscribers[event::class.java] as CopyOnWriteArraySet<Listener<T>>?)?.forEach {
                it.invoke(event)
            }
        }

        return event
    }

    override fun <T: ICancellable> dispatch(event: T): T {
        if (this.subscribers[event::class.java]?.size != 0) {
            val listeners = this.subscribers[event::class.java] as CopyOnWriteArraySet<Listener<T>>?

            if (listeners != null) {
                for (listener in listeners) {
                    listener.invoke(event)

                    if (event.isCancelled) break
                }
            }
        }

        return event
    }

    private fun asListener(parent: Any, method: Method, priority: Int): MethodListener<*> {
        method.trySetAccessible()

        return MethodListener(method, priority, parent, method.parameters[0]::class.java)
    }

    private fun asListener(parent: Any, field: Field): LambdaListener<*> {
        field.trySetAccessible()

        return field.get(parent) as LambdaListener<*>
    }
}
