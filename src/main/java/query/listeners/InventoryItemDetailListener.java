package query.listeners;

import com.google.common.eventbus.Subscribe;
import domain.events.*;
import query.InMemoryViewRepository;
import query.dto.InventoryItemListDto;

public class InventoryItemDetailListener {

    private final InMemoryViewRepository repository;

    public InventoryItemDetailListener() {
        this.repository = InMemoryViewRepository.getInstance();
    }

    @Subscribe
    public void consume(InventoryItemCreated event) {
        System.out.println("item created");
    }

    @Subscribe
    public void consume(InventoryItemDeactivated event) {
        System.out.println("item deactivated");
    }

    @Subscribe
    public void consume(InventoryItemRenamed event) {
        System.out.println("item renamed");
    }

    @Subscribe
    public void consume(ItemsRemovedFromInventory event) {
        System.out.println("item removed from inventory");
    }

    @Subscribe
    public void consume(ItemsCheckedInToInventory event) {
        System.out.println("item checked into inventory");
    }

}
