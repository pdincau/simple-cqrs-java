package infrastructure;

import com.google.common.eventbus.AsyncEventBus;
import domain.events.Event;
import domain.events.EventPublisher;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newCachedThreadPool;

public class EventBus implements EventPublisher {

    private static final ExecutorService threadPool = newCachedThreadPool();
    private final AsyncEventBus bus;

    public EventBus(Object ... listeners) {
        this.bus = new AsyncEventBus(threadPool);
        for (Object l: listeners) {
            this.bus.register(l);
        }
    }

    @Override
    public void publish(Event event) {
        bus.post(event);
    }
}
