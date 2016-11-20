package domain.commands;

import java.util.UUID;

public class CheckInItemsToInventory extends Command {

    public final int count;
    public final int originalVersion;

    public CheckInItemsToInventory(UUID inventoryItemId, int count, int originalVersion) {
        super(inventoryItemId);
        this.count = count;
        this.originalVersion = originalVersion;
    }
}
