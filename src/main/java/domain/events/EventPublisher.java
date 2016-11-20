package domain.events;

public interface EventPublisher {

    void publish(Event event);
}
