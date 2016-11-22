import com.spotify.apollo.Environment;
import com.spotify.apollo.RequestContext;
import com.spotify.apollo.Response;
import com.spotify.apollo.httpservice.HttpService;
import com.spotify.apollo.httpservice.LoadingException;
import com.spotify.apollo.route.Route;
import domain.commands.CommandSender;
import domain.commands.CreateInventoryItem;
import domain.commands.DeactivateInventoryItem;
import domain.commands.handlers.InventoryCommandHandlers;
import domain.events.EventPublisher;
import domain.events.EventStore;
import domain.events.handlers.InventoryEventHandler;
import infrastructure.CommandBus;
import infrastructure.EventBus;
import infrastructure.InMemoryEventStore;
import infrastructure.InventoryItemRepository;
import okio.ByteString;

import java.util.UUID;

import static com.spotify.apollo.Status.ACCEPTED;
import static com.spotify.apollo.Status.CREATED;

public class Api {

    private static final InventoryEventHandler view = new InventoryEventHandler();
    private static final EventPublisher eventBus = new EventBus(view);
    private static final EventStore eventStore = new InMemoryEventStore(eventBus);
    private static final InventoryItemRepository inventoryItemRepository = new InventoryItemRepository(eventStore);
    private static final InventoryCommandHandlers handler = new InventoryCommandHandlers(inventoryItemRepository);
    private static final CommandSender commandBus = new CommandBus(handler);

    public static void main(String[] args) throws LoadingException {
        HttpService.boot(Api::init, "api", args);
    }

    private static void init(Environment environment) {
        environment.routingEngine()
                .registerAutoRoute(Route.sync("POST", "/createItem", Api::createItem))
                .registerAutoRoute(Route.sync("POST", "/deactivateItem", Api::deactivateItem))
                .registerAutoRoute(Route.sync("GET", "/ping", context -> "pong"));
    }

    private static Response<ByteString> createItem(RequestContext context)  {
        String name = context.request().parameter("name").orElse("");
        UUID id = UUID.randomUUID();
        commandBus.send(new CreateInventoryItem(id, name));
        return Response.forStatus(CREATED);
    }

    private static Response<ByteString> deactivateItem(RequestContext context)  {
        String id = context.request().parameter("id").orElse("");
        String version = context.request().parameter("version").orElse("");
        commandBus.send(new DeactivateInventoryItem(UUID.fromString(id), Integer.parseInt(version)));
        return Response.forStatus(ACCEPTED);
    }

}
