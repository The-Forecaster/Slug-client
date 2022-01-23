package trans.rights.event.bus;

import java.util.Arrays;

public interface EventBus {

    /**
     * Adds the listener to the registry
     * @param listener event listener instance
     * @author Austin
     */
    void addListener(Object listener);

    /**
     * Removes the listener from the registry
     * @param listener
     * @author Austin
     */
    void removeListener(Object listener);

    /**
     * Check if an object is currently in the registry
     * @param listener object to check
     * @return if the object is in the registry
     */
    boolean isListener(Object listener);

    /**
     * Post an object to be consumed by the subscribed listeners
     * @param <T> event type
     * @param event object to post
     * @author Austin
     */
    void dispatch(Object event);

    default void addAll(Object... listeners) {
        Arrays.stream(listeners).forEach(this::addListener);
    }

    default void addAll(Iterable<?> listeners) {
        listeners.forEach(this::addListener);
    }

    default void removeAll(Object... listeners) {
        Arrays.stream(listeners).forEach(this::removeListener);
    }

    default void removeAll(Iterable<?> listeners) {
        listeners.forEach(this::removeListener);
    }
}
