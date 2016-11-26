package query.dto;

import java.util.UUID;

public class InventoryItemListDto {

    public UUID id;
    public String name;

    public InventoryItemListDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

}
