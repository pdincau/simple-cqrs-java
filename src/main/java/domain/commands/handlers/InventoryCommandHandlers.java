package domain.commands.handlers;

import domain.commands.*;
import domain.AggregateRootRepository;
import domain.InventoryItem;
import com.google.common.eventbus.Subscribe;
import exceptions.InvalidOperationException;

public class InventoryCommandHandlers {

    private final AggregateRootRepository<InventoryItem> repository;

    public InventoryCommandHandlers(AggregateRootRepository<InventoryItem> repository) {
        this.repository = repository;
    }

    @Subscribe
    public void handle(CreateInventoryItem command) {
        InventoryItem item = new InventoryItem(command.inventoryItemId, command.name);
        repository.save(item, -1);
    }

    @Subscribe
    public void handle(RenameInventoryItem command) {
        InventoryItem item = repository.getById(command.inventoryItemId);
        item.changeName(command.newName);
        repository.save(item, command.originalVersion);
    }

    @Subscribe
    public void handle(RemoveItemsFromInventory command) {
        InventoryItem item = repository.getById(command.inventoryItemId);
        item.remove(command.count);
        repository.save(item, command.originalVersion);
    }

    @Subscribe
    public void handle(CheckInItemsToInventory command) {
        InventoryItem item = repository.getById(command.inventoryItemId);
        item.checkIn(command.count);
        repository.save(item, command.originalVersion);
    }

    @Subscribe
    public void handle(DeactivateInventoryItem command) {
        // TODO: cache successful, unsucessful command result
        try {
            InventoryItem item = repository.getById(command.inventoryItemId);
            item.deactivate();
            repository.save(item, command.originalVersion);
        } catch (InvalidOperationException e) {
            System.out.println("failed command with id: " + command.id + " " + e.getMessage());
        }
    }
}
