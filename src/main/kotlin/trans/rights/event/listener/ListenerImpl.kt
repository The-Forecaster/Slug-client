package trans.rights.event.listener

import kotlin.reflect.KClass
import kotlin.reflect.KFunction

/**
 * This is for making simple, non-verbose listeners
 *
 * @param action consumer the listeners will call when an event is posted
 */
inline fun <reified T : Any> listener(noinline action: (T) -> Unit) = listener(T::class, -50, action)

/**
 * This is for making listeners in Kotlin specifically, as it has less overhead
 *
 * @param T type the lambda will accept
 * @param action consumer the listeners will call when an event is posted
 * @param target class that the listener will listen for
 */
inline fun <reified T : Any> listener(
    target: KClass<T> = T::class, priority: Int = -50, noinline action: (T) -> Unit
) = LambdaListener(target, priority, action)

/** Implementation of [Listener] that uses a lambda function as its target */
open class LambdaListener<T : Any> @PublishedApi internal constructor(
    override inline val target: KClass<T>, override inline val priority: Int, private inline val action: (T) -> Unit
) : Listener<T> {
    override operator fun invoke(param: T) = this.action(param)
}

/**
 * Annotate a listener with this class to mark it for adding to the eventbus registry
 */
annotation class EventHandler
