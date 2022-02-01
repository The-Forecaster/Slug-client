package trans.rights.event.bus.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import trans.rights.event.bus.EventBus;
import trans.rights.event.bus.listener.Listener;
import trans.rights.event.type.ICancellable;

public final class EventManager implements EventBus {
    private final Map<Class<?>, List<Listener<?>>> SUBSCRIBTION_MAP = new ConcurrentHashMap<>();

    @Override
    public void register(Object subscriber) {
        this.SUBSCRIBTION_MAP.get(subscriber.getClass());
    }

    @Override
    public void unregister(Object subscriber) {

    }

    @Override
    public boolean isRegistered(Object subscriber) {
        return false;
    }

    @Override
    public <T> T dispatch(T event) {
        return event;
    }

    @Override
    public <T extends ICancellable> T dispatch(T event) {
        return event;
    }
}
