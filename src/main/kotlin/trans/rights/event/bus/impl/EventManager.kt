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
import kotlin.Comparator

class EventManager(
    override val subscribers: ConcurrentHashMap<Class<*>, CopyOnWriteArraySet<Listener<*>>> = ConcurrentHashMap(),
    private val registeredListeners: MutableSet<Any?> = mutableSetOf()
) : EventBus {

    override fun register(subscriber: Any) {
        if (this.registeredListeners.contains(subscriber)) return

        Arrays.stream(subscriber.javaClass.methods).filter { method ->
            isMethodValid(method)
        }
        .forEach { method -> 
            subscribers.getOrPut(method.parameters[0].javaClass, ::CopyOnWriteArraySet).add(
                asListener(subscriber, method)
            )
        }

        Arrays.stream(subscriber.javaClass.declaredFields).filter { field ->
            field.isAnnotationPresent(EventListener::class.java) && field.get(subscriber) is Listener<*>
        }
        .forEach { field ->
            subscribers.getOrPut(asListener(subscriber, field).target, ::CopyOnWriteArraySet).add(asListener(subscriber, field))
        }

        this.subscribers.values.stream().forEach { listeners ->
            listeners.toSortedSet(Comparator.comparingInt { listener -> listener.priority })
        }

        this.registeredListeners.add(subscribers)
    }

    override fun unregister(subscriber: Any) {
        if (!this.registeredListeners.contains(subscriber)) return

        Arrays.stream(subscriber.javaClass.methods).filter { method ->
            isMethodValid(method)
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
            getOrPutList(event.javaClass).stream().forEach { listener ->
                listener.invoke(event)
            }
        }

        return event
    }

    override fun <T: ICancellable> dispatch(event: T): T {
        if (this.subscribers[event::class.java]?.size != 0) {
            for (listener in getOrPutList(event.javaClass)) {
                listener.invoke(event)

                if (event.isCancelled) break
            }
        }

        return event
    }

    private fun asListener(parent: Any, method: Method): MethodListener<*> {
        method.trySetAccessible()

        return MethodListener(method, method.getAnnotation(EventHandler::class.java).priority, parent, method.parameters[0]::class.java)
    }

    private fun asListener(parent: Any, field: Field): LambdaListener<*> {
        field.trySetAccessible()

        return field.get(parent) as LambdaListener<*>
    }

    private fun <T> getOrPutList(clazz: Class<T>): CopyOnWriteArraySet<Listener<T>> {
        return this.subscribers.getOrPut(clazz, ::CopyOnWriteArraySet) as CopyOnWriteArraySet<Listener<T>>
    }

    private fun isMethodValid(method: Method): Boolean {
        return method.isAnnotationPresent(EventHandler::class.java) && method.parameterCount == 1
    }

}
