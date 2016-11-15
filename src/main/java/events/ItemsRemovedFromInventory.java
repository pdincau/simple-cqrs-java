package events;

import java.util.UUID;

public class ItemsRemovedFromInventory {

    public final UUID id;
    public final int count;

    public ItemsRemovedFromInventory(UUID id, int count) {
        this.id = id;
        this.count = count;
    }
}
