package query;

import query.dto.InventoryItemDetailsDto;
import query.dto.InventoryItemListDto;

import java.util.*;

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

    public InventoryItemDetailsDto details(UUID id) {
        return details.get(id);
    }

}
