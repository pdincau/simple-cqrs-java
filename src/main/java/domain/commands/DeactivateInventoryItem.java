package domain.commands;

import java.util.UUID;

public class DeactivateInventoryItem extends Command {

    public final int originalVersion;

    public DeactivateInventoryItem(UUID id, UUID inventoryItemId, int originalVersion) {
        super(id, inventoryItemId);
        this.originalVersion = originalVersion;
    }
}
