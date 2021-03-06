package domain;

import domain.events.*;
import exceptions.ArgumentException;
import exceptions.InvalidOperationException;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class InventoryItem extends AggregateRoot {

    private Boolean activated;
    private UUID id;
    private String name;

    public InventoryItem() {

    }

    @Override
    public UUID getId() {
        return id;
    }

    public InventoryItem(UUID id, String name) {
        applyChange(new InventoryItemCreated(id, name));
    }

    public void deactivate() {
        if (!activated) {
            throw new InvalidOperationException("item already deactivated");
        }
        applyChange(new InventoryItemDeactivated(id));
    }

    public void remove(int count) {
        if (count <= 0) {
            throw new InvalidOperationException("can't remove negative count from inventory");
        }
        applyChange(new ItemsRemovedFromInventory(id, count));
    }

    public void checkIn(int count) {
        if (count <= 0) {
            throw new InvalidOperationException("must have a count greater than 0 to add to inventory");
        }
        applyChange(new ItemsCheckedInToInventory(id, count));
    }

    public void changeName(String newName) {
        if (StringUtils.isBlank(newName)) {
            throw new ArgumentException("new item name can't be blank");
        }
        applyChange(new InventoryItemRenamed(id, newName));
    }

    @Override
    protected void apply(Event event) {
        // TODO: some cases here are missing in Greg Young's example
        // TODO: usage of instanceof is ugly
        if (event instanceof InventoryItemCreated) {
            id = event.id;
            activated = true;
            name = ((InventoryItemCreated) event).name;
        }

        if (event instanceof InventoryItemDeactivated) {
            activated = false;
        }

        if (event instanceof InventoryItemRenamed) {
            name = ((InventoryItemRenamed) event).newName;
        }
    }
}
