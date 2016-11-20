package domain.events;

import java.util.UUID;

public class InventoryItemRenamed extends Event {

    public final String newName;

    public InventoryItemRenamed(UUID id, String newName) {
        super(id);
        this.newName = newName;
    }
}
