import java.util.UUID;

public class RemoveItemsFromInventory {

    public final UUID inventoryItemId;
    public final int count;
    public final int originalVersion;

    public RemoveItemsFromInventory(UUID inventoryItemId, int count, int originalVersion) {
        this.inventoryItemId = inventoryItemId;
        this.count = count;
        this.originalVersion = originalVersion;
    }
}
