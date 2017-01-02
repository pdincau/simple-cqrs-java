package domain.commands;

import java.util.UUID;

public class CreateInventoryItem extends Command {

    public final String name;

    public CreateInventoryItem(UUID id, UUID inventoryItemId, String name) {
        super(id, inventoryItemId);
        this.name = name;
    }
}