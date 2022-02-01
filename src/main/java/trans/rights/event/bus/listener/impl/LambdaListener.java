package trans.rights.event.bus.listener.impl;

import java.util.Objects;
import java.util.function.Consumer;

import trans.rights.event.bus.listener.Listener;

public final class LambdaListener<T> implements Listener<T> {
    private final Class<T> target;
    
    private Consumer<T> action;

    private final int priority;

    public LambdaListener(Class<T> target, Consumer<T> action, int priority) {
        this.target = target;
        this.action = action;
        this.priority = priority;
    }

    public Class<?> getTarget() {
        return this.target;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public void invoke(T param) {
        this.action.accept(Objects.requireNonNull(param));
    }
}
