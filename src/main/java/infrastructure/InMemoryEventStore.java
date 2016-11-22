package infrastructure;

import domain.events.Event;
import domain.events.EventDescriptor;
import domain.events.EventPublisher;
import domain.events.EventStore;
import exceptions.AggregateNotFoundException;
import exceptions.ConcurrencyException;

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
        List<EventDescriptor> eventDescriptors = current.get(aggregateId);
        if (eventDescriptors == null) {
            eventDescriptors = new ArrayList<>();
            current.put(aggregateId, eventDescriptors);
        } else if(eventDescriptors.get(eventDescriptors.size() - 1).version != expectedVersion && expectedVersion != -1) {
            throw new ConcurrencyException();
        }

        int i = expectedVersion;

        for (Event event : events) {
            i++;
            event.version = i;
            eventDescriptors.add(new EventDescriptor(event, aggregateId, i));
            publisher.publish(event);
        }
    }

    @Override
    public List<Event> getEventsForAggregate(UUID aggregateId) {
        List<EventDescriptor> eventDescriptors = current.get(aggregateId);

        if (eventDescriptors == null) {
            throw new AggregateNotFoundException();
        }

        return eventDescriptors.stream().map(desc -> desc.event).collect(toList());
    }

}
