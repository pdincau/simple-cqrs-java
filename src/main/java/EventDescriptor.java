import events.Event;

import java.util.UUID;

public class EventDescriptor {

    public final Event eventData;
    public final UUID id;
    public final int version;

    public EventDescriptor(Event eventData, UUID id, int version) {
        this.eventData = eventData;
        this.id = id;
        this.version = version;
    }
}
