package commands;

import commands.*;
import domain.AggregateRootRepository;
import domain.InventoryItem;

public class InventoryCommandHandlers {

    private final AggregateRootRepository<InventoryItem> repository;

    public InventoryCommandHandlers(AggregateRootRepository<InventoryItem> repository) {
        this.repository = repository;
    }

    public void handle(CreateInventoryItem command) {
        InventoryItem item = new InventoryItem(command.inventoryItemId, command.name);
        repository.save(item, -1);
    }

    public void handle(RenameInventoryItem command) {
        InventoryItem item = repository.getById(command.inventoryItemId);
        item.changeName(command.newName);
        repository.save(item, command.originalVersion);
    }

    public void handle(RemoveItemsFromInventory command) {
        InventoryItem item = repository.getById(command.inventoryItemId);
        item.remove(command.count);
        repository.save(item, command.originalVersion);
    }

    public void handle(CheckInItemsToInventory command) {
        InventoryItem item = repository.getById(command.inventoryItemId);
        item.checkIn(command.count);
        repository.save(item, command.originalVersion);
    }

    public void handle(DeactivateInventoryItem command) {
        InventoryItem item = repository.getById(command.inventoryItemId);
        item.deactivate();
        repository.save(item, command.originalVersion);
    }
}
