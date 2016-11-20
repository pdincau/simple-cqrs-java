package domain.events;

import java.util.UUID;

public abstract class Event {

    public final UUID id;
    public int version;

    public Event(UUID id) {
        this.id = id;
    }
}
