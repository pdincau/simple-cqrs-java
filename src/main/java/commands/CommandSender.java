package commands;

public interface CommandSender<T> {

    void send(T command);
}
