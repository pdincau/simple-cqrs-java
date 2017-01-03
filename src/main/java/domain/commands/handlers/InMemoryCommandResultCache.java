package domain.commands.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryCommandResultCache implements CommandResultCache {

    Map<UUID, Failure> cache;

    public InMemoryCommandResultCache() {
        this.cache = new HashMap<>();
    }

    @Override
    public void put(UUID id, Failure result) {
        cache.put(id, result);
    }

    @Override
    public Failure get(UUID id) {
        return cache.get(id);
    }
}
