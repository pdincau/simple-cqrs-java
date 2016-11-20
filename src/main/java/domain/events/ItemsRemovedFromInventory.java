package domain.events;

import java.util.UUID;

public class ItemsRemovedFromInventory extends Event {
    
    public final int count;

    public ItemsRemovedFromInventory(UUID id, int count) {
        super(id);
        this.count = count;
    }
}
