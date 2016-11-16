import events.Event;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class InMemoryEventStore implements EventStore {

    private final EventPublisher publisher;

    private final Map<UUID, List<EventDescriptor>> current = new HashMap<>();

    public InMemoryEventStore(EventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void saveEvents(UUID aggregateId, List<Event> events, int expectedVersion) {

    }

    @Override
    public List<Event> getEventsForAggregate(UUID aggregateId) {
        List<EventDescriptor> eventDescriptors = current.get(aggregateId);

        if (eventDescriptors == null) {
            throw new AggregateNotFoundException();
        }

        return eventDescriptors.stream().map(desc -> desc.eventData).collect(toList());
    }

}
