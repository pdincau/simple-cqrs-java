package domain;

import com.google.common.eventbus.AsyncEventBus;
import events.Event;
import events.DomainEventPublisher;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newCachedThreadPool;

public class EventBus implements DomainEventPublisher {

    private static final ExecutorService threadPool = newCachedThreadPool();
    private final AsyncEventBus bus;

    public EventBus() {
        bus = new AsyncEventBus(threadPool);
    }

    @Override
    public void publish(Event event) {
        bus.post(event);
    }
}
