package events;

public interface EventPublisher<T extends Event> {

    void publish(T event);
}
