package query;

import query.dto.InventoryItemDetailsDto;
import query.dto.InventoryItemListDto;

import java.util.*;
import java.util.function.Predicate;

public class InMemoryViewRepository {

    private static InMemoryViewRepository instance;

    private final Map<UUID, InventoryItemDetailsDto> details;
    private final List<InventoryItemListDto> list;

    public static InMemoryViewRepository getInstance() {
        if(instance == null) {
            instance = new InMemoryViewRepository();
    }
        return instance;
    }

    public InMemoryViewRepository() {
        this.details = new HashMap<>();
        this.list = new ArrayList<>();
    }

    public List<InventoryItemListDto> list() {
        return list;
    }

    public void saveListElement(InventoryItemListDto inventoryItemListDto) {
        list.add(inventoryItemListDto);
    }

    public void removeListElement(UUID id) {
        list.removeIf(element -> (element.id).equals(id));
    }

    public InventoryItemDetailsDto details(UUID id) {
        return details.get(id);
    }

    public void updateListElement(InventoryItemListDto inventoryItemListDto) {
        list.removeIf(element -> (element.id).equals(inventoryItemListDto.id));
        list.add(inventoryItemListDto);
    }
}
