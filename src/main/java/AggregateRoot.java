import events.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AggregateRoot {

    private final List<Event> changes = new ArrayList<>();
    private int version;

    public abstract UUID getId();
    protected abstract void apply(Event event);

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    public List<Event> getUncommittedChanges() {
        return changes;
    }

    public void MarkChangesAsCommitted() {
        changes.clear();
    }

    public void loadsFromHistory(List<Event> history) {
        for (Event event : history) {
            applyChange(event, false);
        }
    }

    private void applyChange(Event event, Boolean isNew) {
        apply(event);
        if (isNew) {
            changes.add(event);
        }
    }

}
