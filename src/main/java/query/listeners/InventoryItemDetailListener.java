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
        System.out.println(this.getClass().getCanonicalName() + " item created handled");
    }

    @Subscribe
    public void consume(InventoryItemDeactivated event) {
        System.out.println(this.getClass().getCanonicalName() + " item deactivated handled");
    }

    @Subscribe
    public void consume(InventoryItemRenamed event) {
        System.out.println(this.getClass().getCanonicalName() + " item renamed handled");
    }

    @Subscribe
    public void consume(ItemsRemovedFromInventory event) {
        System.out.println(this.getClass().getCanonicalName() + " item removed from inventory handled");
    }

    @Subscribe
    public void consume(ItemsCheckedInToInventory event) {
        System.out.println(this.getClass().getCanonicalName() + " item checked into inventory handled");
    }

}
