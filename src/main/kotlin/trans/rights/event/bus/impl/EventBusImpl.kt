package trans.rights.event.bus.impl

import java.lang.reflect.Field
import java.lang.reflect.Method
import java.util.Arrays
import java.util.concurrent.CopyOnWriteArraySet
import trans.rights.event.annotation.EventHandler
import trans.rights.event.bus.AbstractEventBus
import trans.rights.event.bus.ListenerType.LAMBDA
import trans.rights.event.listener.Listener
import trans.rights.event.listener.impl.LambdaListener
import trans.rights.event.listener.impl.MethodListener
import trans.rights.event.type.ICancellable

object BasicEventManager : AbstractEventBus(LAMBDA) {
    override fun registerFields(subscriber: Any) {
        Arrays.stream(subscriber.javaClass.declaredFields).filter(this::isValid).forEach { field ->
            this.registry.getOrPut(field.asListener(subscriber).target, ::CopyOnWriteArraySet).run {
                this.add(field.asListener(subscriber))
                this.toSortedSet(Comparator.comparingInt(Listener<*>::priority))
            }
        }
    }

    override fun registerMethods(subscriber: Any) {
        Arrays.stream(subscriber.javaClass.declaredMethods).filter(Method::isValid).forEach { method ->
            this.registry.getOrPut(method.parameters[0].type, ::CopyOnWriteArraySet).run {
                this.add(method.asListener(subscriber))
                this.toSortedSet(Comparator.comparingInt(Listener<*>::priority))
            }
        }
    }

    override fun unregisterFields(subscriber: Any) {
        Arrays.stream(subscriber.javaClass.declaredFields).filter(this::isValid).forEach { field ->
            this.registry[field.type]?.remove(field.get(subscriber))
        }
    }

    override fun unregisterMethods(subscriber: Any) {
        Arrays.stream(subscriber.javaClass.declaredMethods).filter(Method::isValid).forEach { method ->
            this.registry[method.parameters[0].type]?.remove(method.asListener(subscriber))
        }
    }

    fun <T : ICancellable> dispatch(event: T): T {
        if (this.registry[event::class.java]?.size != 0) {
            for (listener in this.getOrPutList(event.javaClass)) {
                listener.invoke(event)

                if (event.isCancelled) break
            }
        }

        return event
    }

    private fun isValid(field: Field): Boolean = field.isAnnotationPresent(EventHandler::class.java) && field.javaClass.isAssignableFrom(Listener::class.java)
}

internal fun Method.isValid(): Boolean = this.isAnnotationPresent(EventHandler::class.java) && this.parameterCount == 1

internal fun Method.asListener(parent: Any): MethodListener<*> {
    this.trySetAccessible()

    return MethodListener(
        this,
        this.getAnnotation(EventHandler::class.java).priority,
        parent,
        this.parameters[0]::class.java
    )
}

internal fun Field.asListener(parent: Any): LambdaListener<*> {
    this.trySetAccessible()

    return this.get(parent) as LambdaListener<*>
}
