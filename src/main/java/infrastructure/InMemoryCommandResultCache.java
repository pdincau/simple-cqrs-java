package infrastructure;

import domain.commands.handlers.CommandResultCache;
import domain.commands.results.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryCommandResultCache implements CommandResultCache {

    Map<UUID, Result> cache;

    public InMemoryCommandResultCache() {
        this.cache = new HashMap<>();
    }

    @Override
    public void put(UUID id, Result result) {
        cache.put(id, result);
    }

    @Override
    public Result get(UUID id) {
        return cache.get(id);
    }
}
