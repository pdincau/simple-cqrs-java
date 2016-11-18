package domain;

import commands.Command;
import commands.CommandSender;
import events.Event;
import events.EventPublisher;

public class FakeBus implements CommandSender, EventPublisher {


    @Override
    public void send(Command command) {

    }

    @Override
    public void publish(Event event) {
        
    }
}
