package domain.commands.handlers;

import domain.commands.results.Result;

import java.util.UUID;

public interface CommandResultCache {

    void put(UUID id, Result result);

    Result get(UUID id);
}
