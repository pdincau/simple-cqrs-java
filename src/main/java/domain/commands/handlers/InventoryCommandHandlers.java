package domain.commands.handlers;

import domain.commands.*;
import domain.AggregateRootRepository;
import domain.InventoryItem;
import com.google.common.eventbus.Subscribe;

public class InventoryCommandHandlers {

    private final AggregateRootRepository<InventoryItem> repository;
    private final CommandResultCache cache;

    public InventoryCommandHandlers(AggregateRootRepository<InventoryItem> repository, CommandResultCache cache) {
        this.repository = repository;
        this.cache = cache;
    }

    @Subscribe
    public void handle(CreateInventoryItem command) {
        try {
            InventoryItem item = new InventoryItem(command.inventoryItemId, command.name);
            repository.save(item, -1);
            cache.put(command.id, new Success(command.inventoryItemId));
        } catch (Exception e) {
            Failure result = new Failure(command.inventoryItemId, e.getMessage());
            cache.put(command.id, result);
        }
    }

    @Subscribe
    public void handle(RenameInventoryItem command) {
        try {
            InventoryItem item = repository.getById(command.inventoryItemId);
            item.changeName(command.newName);
            repository.save(item, command.originalVersion);
            cache.put(command.id, new Success(command.inventoryItemId));
        } catch (Exception e) {
            Failure result = new Failure(command.inventoryItemId, e.getMessage());
            cache.put(command.id, result);
        }
    }

    @Subscribe
    public void handle(RemoveItemsFromInventory command) {
        try {
            InventoryItem item = repository.getById(command.inventoryItemId);
            item.remove(command.count);
            repository.save(item, command.originalVersion);
            cache.put(command.id, new Success(command.inventoryItemId));
        } catch (Exception e) {
            Failure result = new Failure(command.inventoryItemId, e.getMessage());
            cache.put(command.id, result);
        }
    }

    @Subscribe
    public void handle(CheckInItemsToInventory command) {
        try {
            InventoryItem item = repository.getById(command.inventoryItemId);
            item.checkIn(command.count);
            repository.save(item, command.originalVersion);
            cache.put(command.id, new Success(command.inventoryItemId));
        } catch (Exception e) {
            Failure result = new Failure(command.inventoryItemId, e.getMessage());
            cache.put(command.id, result);
        }
    }

    @Subscribe
    public void handle(DeactivateInventoryItem command) {
        try {
            InventoryItem item = repository.getById(command.inventoryItemId);
            item.deactivate();
            repository.save(item, command.originalVersion);
            cache.put(command.id, new Success(command.inventoryItemId));
        } catch (Exception e) {
            Failure result = new Failure(command.inventoryItemId, e.getMessage());
            cache.put(command.id, result);
        }
    }
}
