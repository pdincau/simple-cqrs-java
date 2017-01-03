package domain.commands.handlers;

import java.util.UUID;

public interface CommandResultCache {

    void put(UUID id, Failure result);

    Failure get(UUID id);
}
