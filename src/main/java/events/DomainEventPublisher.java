package events;

public interface DomainEventPublisher {

    void publish(Event event);
}
