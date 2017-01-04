import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.spotify.apollo.Environment;
import com.spotify.apollo.RequestContext;
import com.spotify.apollo.Response;
import com.spotify.apollo.httpservice.HttpService;
import com.spotify.apollo.httpservice.LoadingException;
import com.spotify.apollo.route.Route;
import domain.commands.CommandSender;
import domain.commands.CreateInventoryItem;
import domain.commands.DeactivateInventoryItem;
import domain.commands.handlers.*;
import domain.events.EventPublisher;
import domain.events.EventStore;
import infrastructure.CommandBus;
import infrastructure.EventBus;
import infrastructure.InMemoryEventStore;
import infrastructure.InventoryItemRepository;
import okio.ByteString;
import query.ReadModelFacade;
import query.dto.InventoryItemDetailsDto;
import query.dto.InventoryItemListDto;
import query.listeners.InventoryItemDetailListener;
import query.listeners.InventoryItemListListener;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.spotify.apollo.Status.*;
import static okio.ByteString.encodeUtf8;

public class Api {

    private static final CommandResultCache cache = new InMemoryCommandResultCache();
    private static final InventoryItemListListener list = new InventoryItemListListener();
    private static final InventoryItemDetailListener detail = new InventoryItemDetailListener();
    private static final EventPublisher eventBus = new EventBus(list, detail);
    private static final EventStore eventStore = new InMemoryEventStore(eventBus);
    private static final InventoryItemRepository inventoryItemRepository = new InventoryItemRepository(eventStore);
    private static final InventoryCommandHandlers handler = new InventoryCommandHandlers(inventoryItemRepository, cache);
    private static final CommandSender commandBus = new CommandBus(handler);

    private static ReadModelFacade readModel = new ReadModelFacade();

    public static void main(String[] args) throws LoadingException {
        HttpService.boot(Api::init, "api", args);
    }

    private static void init(Environment environment) {
        environment.routingEngine()
                .registerAutoRoute(Route.sync("POST", "/createItem", Api::createItem))
                .registerAutoRoute(Route.sync("POST", "/deactivateItem", Api::deactivateItem))
                .registerAutoRoute(Route.sync("GET", "/items", Api::items))
                .registerAutoRoute(Route.sync("GET", "/item", Api::item))
                .registerAutoRoute(Route.sync("GET", "/command-status", Api::commandStatus))
                .registerAutoRoute(Route.sync("GET", "/ping", context -> "pong"));
    }

    private static Response<ByteString> items(RequestContext context) {
        List<InventoryItemListDto> inventoryItems = readModel.getInventoryItems();
        String body = new Gson().toJson(inventoryItems);
        return Response.forStatus(OK).withHeaders(headers()).withPayload(encodeUtf8(body));
    }

    private static Response<ByteString> item(RequestContext context) {
        String id = context.request().parameter("id").orElse("");
        InventoryItemDetailsDto inventoryItemDetails = readModel.getInventoryItemDetails(UUID.fromString(id));
        String body = new Gson().toJson(inventoryItemDetails);
        return Response.forStatus(OK).withHeaders(headers()).withPayload(encodeUtf8(body));
    }

    private static Response<Object> createItem(RequestContext context)  {
        String name = context.request().parameter("name").orElse("");
        UUID aggregateId = UUID.randomUUID();
        UUID commandId = UUID.randomUUID();
        commandBus.send(new CreateInventoryItem(commandId, aggregateId, name));
        return Response.forStatus(CREATED).withHeader("location", locationForCommandResult(commandId));
    }

    private static Response<Object> deactivateItem(RequestContext context)  {
        String id = context.request().parameter("id").orElse("");
        UUID commandId = UUID.randomUUID();
        String version = context.request().parameter("version").orElse("");
        commandBus.send(new DeactivateInventoryItem(commandId, UUID.fromString(id), Integer.parseInt(version)));
        return Response.forStatus(ACCEPTED).withHeader("location", locationForCommandResult(commandId));
    }

    private static Response<Object> commandStatus(RequestContext context) {
        String id = context.request().parameter("id").orElse("");
        Result result = cache.get(UUID.fromString(id));

        if (result == null) {
            return Response.forStatus(NOT_FOUND);
        }
        String body = new Gson().toJson(result);
        return Response.forStatus(OK).withPayload(encodeUtf8(body));
    }


    private static Map<String, String> headers() {
        return ImmutableMap.<String, String>builder()
                .put("Content-Type", "application/json")
                .put("charset", "utf8")
                .build();
    }

    private static String locationForCommandResult(UUID commandId) {
        return "/command-status?id=" + commandId.toString();
    }

}
