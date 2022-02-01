package trans.rights.event.bus.listener;

/**
 * Basic structure for an event listener and invoke.
 * 
 * @author Austin
 */
public interface Listener<T> extends Comparable<Listener<T>> {

    /**
     * Gets the priority that the listener will be called upon(use wisely)
     * 
     * @return Priority of this listener
     */
    int getPriority();

    /**
     * Processes an event passed through this listener
     * 
     * @param Event that is being processed
     */
    void invoke(T param);

    @Override
    default int compareTo(Listener<T> listener) {
        return Math.max(this.getPriority(), listener.getPriority());
    }
}
