package trans.rights.event.bus.impl

import java.lang.reflect.Field
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArraySet
import trans.rights.event.annotation.EventHandler
import trans.rights.event.bus.EventBus
import trans.rights.event.listener.impl.MethodListener
import trans.rights.event.listener.impl.LambdaListener
import trans.rights.event.listener.Listener
import trans.rights.event.type.ICancellable
import java.lang.reflect.Method
import kotlin.Comparator

open class BasicEventManager() : EventBus {
    override val subscribers: ConcurrentHashMap<Class<*>, CopyOnWriteArraySet<Listener<*>>> = ConcurrentHashMap()

    private val registeredListeners: MutableSet<Any?> = mutableSetOf()

    override fun register(subscriber: Any) {
        if (isRegistered(subscriber)) return

        Arrays.stream(subscriber.javaClass.declaredMethods).filter { method -> method.isValid() }
        .forEach { method ->
            subscribers.getOrPut(method.parameters[0].javaClass, ::CopyOnWriteArraySet).add(
                method.asListener(subscriber)
            )
        }

        Arrays.stream(subscriber.javaClass.declaredFields).filter { field -> field.isValid(subscriber) }
        .forEach { field ->
            subscribers.getOrPut(field.asListener(subscriber).target, ::CopyOnWriteArraySet).add(field.asListener(subscriber))
        }

        this.subscribers.values.stream().forEach { listeners ->
            listeners.toSortedSet(Comparator.comparing { listener -> listener })
        }

        this.registeredListeners.add(subscribers)
    }

    override fun unregister(subscriber: Any) {
        if (!isRegistered(subscriber)) return

        Arrays.stream(subscriber.javaClass.declaredMethods).filter { method ->
            method.isValid()
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

    private fun <T> getOrPutList(clazz: Class<T>): CopyOnWriteArraySet<Listener<T>> {
        return this.subscribers.getOrPut(clazz, ::CopyOnWriteArraySet) as CopyOnWriteArraySet<Listener<T>>
    }

    private fun fieldAsListener(field: Field, parent: Any) {
        try {
            
        }
        catch(e: IllegalAccessException)
    }
}

object AnnotatedEventBus : BasicEventManager() {}

private fun Method.isValid(): Boolean = this.isAnnotationPresent(EventHandler::class.java) && this.parameterCount == 1

private fun Field.isValid(parent: Any): Boolean = this.isAnnotationPresent(EventHandler::class.java) && this.get(parent) is Listener<*>

private fun Method.asListener(parent: Any): MethodListener<*> {
    this.trySetAccessible()

    return MethodListener(this, this.getAnnotation(EventHandler::class.java).priority, parent, this.parameters[0]::class.java)
}

private fun Field.asListener(parent: Any): LambdaListener<*> {
    this.trySetAccessible()

    return this.get(parent) as LambdaListener<*>
}
