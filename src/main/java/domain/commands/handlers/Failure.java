package domain.commands.handlers;

import java.util.UUID;

public class Failure extends Result {

    private final String error;

    public Failure(UUID aggregateId, String error) {
        super(aggregateId);
        this.error = error;
    }

}
