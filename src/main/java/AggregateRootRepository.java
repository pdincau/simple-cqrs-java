import java.util.UUID;

public interface AggregateRootRepository<T extends AggregateRoot> {

    void save(T aggregate, int expectedVersion);

    T getById(UUID id);
}
