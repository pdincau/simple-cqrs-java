package query.dto;

import java.util.UUID;

public class InventoryItemDetailsDto {

    private UUID id;
    private String name;
    private int currentCount;
    private int version;

    public InventoryItemDetailsDto(UUID id, String name, int currentCount, int version) {
        this.id = id;
        this.name = name;
        this.currentCount = currentCount;
        this.version = version;
    }
}
