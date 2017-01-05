package domain.commands;

import java.util.UUID;

public abstract class Command {

    public final UUID id;
    public final UUID inventoryItemId;

    public Command(UUID id, UUID inventoryItemId) {
        this.id = id;
        this.inventoryItemId = inventoryItemId;
    }
}
