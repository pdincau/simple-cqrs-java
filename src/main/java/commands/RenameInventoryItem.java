package commands;

import java.util.UUID;

public class RenameInventoryItem {

    public final UUID inventoryItemId;
    public final String newName;
    public final int originalVersion;

    public RenameInventoryItem(UUID inventoryItemId, String newName, int originalVersion) {
        this.inventoryItemId = inventoryItemId;
        this.newName = newName;
        this.originalVersion = originalVersion;
    }
}