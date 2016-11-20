package domain;

import events.Event;
import events.DomainEventPublisher;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newCachedThreadPool;

public class EventBus implements DomainEventPublisher {

    private static final ExecutorService threadPool = newCachedThreadPool();
    private final EventPublisher publisher;

    public EventBus() {
        publisher = new AsyncEventBus(threadPool);
    }

    @Override
    public void publish(Event event) {
        publisher.publish(event);
    }
}
