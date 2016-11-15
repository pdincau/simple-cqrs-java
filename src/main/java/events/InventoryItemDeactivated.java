package events;

import java.util.UUID;

public class InventoryItemDeactivated extends Event {

    public InventoryItemDeactivated(UUID id) {
        super(id);
    }
}
