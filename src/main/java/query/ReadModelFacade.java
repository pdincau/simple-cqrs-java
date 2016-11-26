package query;

import query.dto.InventoryItemDetailsDto;
import query.dto.InventoryItemListDto;

import java.util.List;
import java.util.UUID;

public class ReadModelFacade {

    private final InMemoryViewRepository repository;

    public ReadModelFacade() {
        this.repository = InMemoryViewRepository.getInstance();
    }

    public List<InventoryItemListDto> getInventoryItems() {
        return repository.list();
    }

    public InventoryItemDetailsDto getInventoryItemDetails(UUID id) {
        return repository.details(id);
    }
}
