package domain.commands;

import java.util.UUID;

public class CheckInItemsToInventory extends Command {

    public final int count;
    public final int originalVersion;

    public CheckInItemsToInventory(UUID id, UUID inventoryItemId, int count, int originalVersion) {
        super(id, inventoryItemId);
        this.count = count;
        this.originalVersion = originalVersion;
    }
}
