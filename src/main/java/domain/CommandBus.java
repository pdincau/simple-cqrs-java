package domain;

import com.google.common.eventbus.AsyncEventBus;
import commands.Command;
import commands.CommandSender;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newCachedThreadPool;

public class CommandBus implements CommandSender {

    private static final ExecutorService threadPool = newCachedThreadPool();
    private final AsyncEventBus bus;

    public CommandBus() {
        this.bus = new AsyncEventBus(threadPool);
    }

    @Override
    public void send(Command command) {
        bus.post(command);
    }
}
