package events;

import java.util.UUID;

public class InventoryItemCreated extends Event {

    public final String name;

    public InventoryItemCreated(UUID id, String name) {
        super(id);
        this.name = name;
    }
}
