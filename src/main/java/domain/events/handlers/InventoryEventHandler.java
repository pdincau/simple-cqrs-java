package domain.events.handlers;

import com.google.common.eventbus.Subscribe;
import domain.events.InventoryItemCreated;

public class InventoryEventHandler {

    @Subscribe
    public void consume(InventoryItemCreated event) {
        System.out.println("an InventoryItem was created");
    }
}
