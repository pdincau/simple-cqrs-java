package infrastructure;

import com.google.common.eventbus.AsyncEventBus;
import domain.commands.Command;
import domain.commands.CommandSender;
import domain.commands.handlers.InventoryCommandHandlers;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newCachedThreadPool;

public class CommandBus implements CommandSender {

    private static final ExecutorService threadPool = newCachedThreadPool();
    private final AsyncEventBus bus;

    public CommandBus(InventoryCommandHandlers handler) {
        this.bus = new AsyncEventBus(threadPool);
        this.bus.register(handler);
    }

    @Override
    public void send(Command command) {
        bus.post(command);
    }
}
