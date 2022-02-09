package trans.rights.client.misc;

import trans.rights.event.bus.EventBus;
import trans.rights.event.bus.impl.BasicEventManager;

public interface EventPoster extends Globals {
    EventBus EVENT_BUS = new BasicEventManager();

    default EventBus getEventBus() {
        return EVENT_BUS;
    }
}
