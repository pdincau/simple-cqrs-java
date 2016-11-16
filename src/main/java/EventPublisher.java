import events.Event;

public interface EventPublisher<T extends Event> {

    void publish(T event);
}
