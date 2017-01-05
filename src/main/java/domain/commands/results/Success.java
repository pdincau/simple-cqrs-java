package domain.commands.results;

import java.util.UUID;

public class Success extends Result {

    public Success(UUID inventoryItemId) {
        super(inventoryItemId);
    }
}
