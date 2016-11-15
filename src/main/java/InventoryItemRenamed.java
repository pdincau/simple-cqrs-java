import java.util.UUID;

public class InventoryItemRenamed {

    public final UUID id;
    public final String newName;

    public InventoryItemRenamed(UUID id, String newName) {
        this.id = id;
        this.newName = newName;
    }
}
