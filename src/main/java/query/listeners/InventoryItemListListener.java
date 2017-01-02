package query.listeners;

import com.google.common.eventbus.Subscribe;
import domain.events.InventoryItemCreated;
import domain.events.InventoryItemDeactivated;
import domain.events.InventoryItemRenamed;
import query.InMemoryViewRepository;

public class InventoryItemListListener {

    private final InMemoryViewRepository repository;

    public InventoryItemListListener() {
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


}
