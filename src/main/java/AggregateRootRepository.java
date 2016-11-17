import java.util.UUID;

public interface AggregateRootRepository<T extends AggregateRoot> {

    void save(AggregateRoot aggregate, int expectedVersion);

    T getById(UUID id);
}
