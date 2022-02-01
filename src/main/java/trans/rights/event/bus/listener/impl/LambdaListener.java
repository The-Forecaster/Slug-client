package trans.rights.event.bus.listener.impl;

import java.util.Objects;
import java.util.function.Consumer;

import trans.rights.event.bus.listener.Listener;

public final class LambdaListener<T> implements Listener<T> {
    private final Class<T> target;

    private final Object parent;
    
    private final Consumer<T> action;

    private final int priority;

    public LambdaListener(final Class<T> target, final Object parent, final Consumer<T> action, final int priority) {
        this.target = target;
        this.parent = parent;
        this.action = action;
        this.priority = priority;
    }

    public Class<?> getTarget() {
        return this.target;
    }

    @Override
    public Object getParent() {
        return this.parent;
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
