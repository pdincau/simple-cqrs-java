package domain.commands;

import java.util.UUID;

public class RenameInventoryItem extends Command {

    public final String newName;
    public final int originalVersion;

    public RenameInventoryItem(UUID id, UUID inventoryItemId, String newName, int originalVersion) {
        super(id, inventoryItemId);
        this.newName = newName;
        this.originalVersion = originalVersion;
    }
}