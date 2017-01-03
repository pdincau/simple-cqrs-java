package domain.commands.handlers;

import java.util.UUID;

public class Failure {

    private final UUID aggregateId;
    private final String message;

    public Failure(UUID aggregateId, String message) {
        this.aggregateId = aggregateId;
        this.message = message;
    }

}
