package infrastructure;

import events.Event;
import events.EventDescriptor;
import events.DomainEventPublisher;
import events.EventStore;
import exceptions.AggregateNotFoundException;
import exceptions.ConcurrencyException;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class InMemoryEventStore implements EventStore {

    private final DomainEventPublisher publisher;

    private final Map<UUID, List<EventDescriptor>> current = new HashMap<>();

    public InMemoryEventStore(DomainEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void saveEvents(UUID aggregateId, List<Event> events, int expectedVersion) {
        List<EventDescriptor> eventDescriptors = current.get(aggregateId);

        if (eventDescriptors == null) {
            current.put(aggregateId, new ArrayList<>());
        } else if(eventDescriptors.get(eventDescriptors.size() - 1).version != expectedVersion && expectedVersion != -1) {
            throw new ConcurrencyException();
        }

        int i = expectedVersion;

        for (Event event : events) {
            // TODO: do we really need events.EventDescriptor.version ?
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
