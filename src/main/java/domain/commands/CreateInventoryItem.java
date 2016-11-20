package domain.commands;

import java.util.UUID;

public class CreateInventoryItem extends Command {

    public final String name;

    public CreateInventoryItem(UUID inventoryItemId, String name) {
        super(inventoryItemId);
        this.name = name;
    }
}