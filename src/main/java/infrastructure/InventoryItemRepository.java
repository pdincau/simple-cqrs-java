package infrastructure;

import domain.AggregateRootRepository;
import domain.events.EventStore;
import domain.InventoryItem;
import domain.events.Event;

import java.util.List;
import java.util.UUID;

public class InventoryItemRepository implements AggregateRootRepository<InventoryItem> {

    private final EventStore storage;

    public InventoryItemRepository(EventStore storage) {
        this.storage = storage;
    }

    @Override
    public void save(InventoryItem inventoryItem, int expectedVersion) {
        storage.saveEvents(inventoryItem.getId(), inventoryItem.getUncommittedChanges(), expectedVersion);
        //TODO: check whether this is correct
        inventoryItem.markChangesAsCommitted();
    }

    @Override
    public InventoryItem getById(UUID id) {
        InventoryItem inventoryItem = new InventoryItem();
        List<Event> events = storage.getEventsForAggregate(id);
        inventoryItem.loadsFromHistory(events);
        //TODO: check whether this is correct
        inventoryItem.setVersion(events.get(events.size()-1).version);
        return inventoryItem;
    }

}
