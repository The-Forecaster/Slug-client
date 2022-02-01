package trans.rights.event.bus.listener.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import trans.rights.client.TransRights;
import trans.rights.event.bus.listener.Listener;

public final class MethodListener<T> implements Listener<T> {
    private final Class<T> target;

    private final Object parent;

    private final Method method;

    private final int priority;

    public MethodListener(Class<T> target, Object parent, Method method, int priority) {
        this.target = target;
        this.parent = parent;
        this.method = method;
        this.priority = priority;

        method.setAccessible(true);
    }

    public Class<T> getTarget() {
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
        if (method.canAccess(this)) {
            try {
                method.invoke(method.getDeclaringClass(), param);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                if (e instanceof IllegalAccessException) TransRights.LOGGER.error("EventHandler method is either protected or private");
                if (e instanceof IllegalArgumentException) TransRights.LOGGER.error("Method has too many/incorrect arguemnts");
            }
        }
    }
}
