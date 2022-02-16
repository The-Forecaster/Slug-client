package trans.rights.client.misc.api;

import net.minecraft.util.math.Vec3d;
import trans.rights.event.bus.EventBus;
import trans.rights.event.bus.impl.BasicEventManager;

public interface EventObject extends Globals {
    default EventBus getEventBus() {
        return BasicEventManager.INSTANCE;
    }

    
}
