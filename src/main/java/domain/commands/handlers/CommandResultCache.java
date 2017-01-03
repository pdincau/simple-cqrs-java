package domain.commands.handlers;

import java.util.UUID;

public interface CommandResultCache {

    void put(UUID id, Result result);

    Result get(UUID id);
}
