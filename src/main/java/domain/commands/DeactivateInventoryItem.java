package domain.commands;

import java.util.UUID;

public class DeactivateInventoryItem extends Command {

    public final int originalVersion;

    public DeactivateInventoryItem(UUID inventoryItemId, int originalVersion) {
        super(inventoryItemId);
        this.originalVersion = originalVersion;
    }
}
