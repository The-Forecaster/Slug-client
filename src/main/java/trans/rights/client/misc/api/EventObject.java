package trans.rights.client.misc.api;

import trans.rights.event.bus.EventBus;
import trans.rights.event.bus.impl.BasicEventManager;

public interface EventObject extends Globals {
    EventBus EVENT_BUS = new BasicEventManager();

    default EventBus getEventBus() {
        return EVENT_BUS;
    }
}
