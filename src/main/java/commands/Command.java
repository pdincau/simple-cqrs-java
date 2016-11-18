package commands;

import java.util.UUID;

public abstract class Command {

    public final UUID inventoryItemId;

    public Command(UUID inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }
}
