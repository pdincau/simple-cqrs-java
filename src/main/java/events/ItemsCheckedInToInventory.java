package events;

import java.util.UUID;

public class ItemsCheckedInToInventory extends Event {

    public final int count;

    public ItemsCheckedInToInventory(UUID id, int count) {
        super(id);
        this.count = count;
    }
}
