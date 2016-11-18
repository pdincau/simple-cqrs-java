package commands;

import java.util.UUID;

public class RenameInventoryItem extends Command {

    public final String newName;
    public final int originalVersion;

    public RenameInventoryItem(UUID inventoryItemId, String newName, int originalVersion) {
        super(inventoryItemId);
        this.newName = newName;
        this.originalVersion = originalVersion;
    }
}