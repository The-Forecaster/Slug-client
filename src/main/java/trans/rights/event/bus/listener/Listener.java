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
     * @return int Priority of this listener
     */
    int getPriority();

    /**
     * Processes an event passed through this listener
     * 
     * @param param event object that is being processed
     */
    void invoke(T param);

    /**
     * Gets the object that this listener was defined in
     *
     * @return Object the parent
     */
    Object getParent();

    @Override
    default int compareTo(Listener<T> listener) {
        return Math.max(this.getPriority(), listener.getPriority());
    }
}
