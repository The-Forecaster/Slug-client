package trans.rights.event.bus;

import trans.rights.event.type.ICancellable;

/**
 * Basic structure for an event dispatcher
 * 
 * @author Austin
 */
public abstract interface EventBus {

    /**
     * Adds the Subscriber to the registry
     * 
     * @param Subscriber event Subscriber instance
     */
    void register(Object subscriber);

    /**
     * Removes the Subscriber from the registry
     * 
     * @param Subscriber
     */
    void unregister(Object subscriber);

    /**
     * Check if an object is currently in the registry
     * 
     * @param Subscriber object to check
     * @return if the object is in the registry
     */
    boolean isRegistered(Object subscriber);

    /**
     * Post an event to be processed by the subscribed methods or listener objects
     * 
     * @param <T> event type
     * @param event object to post
     * 
     * @return the event you passed
     */
    <T> T dispatch(T event);

    /**
     * Posts a cancellable event to be processed by the subscribed methods or listener objects, 
     * if an event is cancelled then lower priority events will not be invoked
     * 
     * @param <T>
     * @param event cancellable event to post
     * 
     * @return the event you passed
     */
    <T extends ICancellable> T dispatch(T event);
}
