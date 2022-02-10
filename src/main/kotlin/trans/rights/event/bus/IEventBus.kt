package trans.rights.event.bus

import java.util.concurrent.CopyOnWriteArraySet
import trans.rights.event.listener.Listener
import trans.rights.event.type.ICancellable

/**
 * Basic structure for an event dispatcher
 *
 * @author Austin
 */
interface EventBus {

    /**
     * map for listeners and their target events
     *
     * <Event, Set<Listener>>
     */
    val subscribers: MutableMap<Class<*>, CopyOnWriteArraySet<Listener<*>>>

    /**
     * Adds the Subscriber to the registry
     *
     * @param subscriber event Subscriber instance
     */
    fun register(subscriber: Any)

    /**
     * Removes the Subscriber from the registry
     *
     * @param subscriber event subscriber instance
     */
    fun unregister(subscriber: Any)

    /**
     * Check if an object is currently in the registry
     *
     * @return if the object is in the registry
     */
    fun isRegistered(subscriber: Any): Boolean

    /**
     * Post an event to be processed by the subscribed methods or listener objects
     *
     * @param <T> event type
     * @param event object to post
     *
     * @return the event you passed
     */
    fun <T: Any> dispatch(event: T): T
}
