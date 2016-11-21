import com.spotify.apollo.Environment;
import com.spotify.apollo.httpservice.HttpService;
import com.spotify.apollo.httpservice.LoadingException;
import com.spotify.apollo.route.Route;

public class Api {

    public static void main(String[] args) throws LoadingException {
        HttpService.boot(Api::init, "api", args);
    }

    private static void init(Environment environment) {
        environment.routingEngine()
                .registerAutoRoute(Route.sync("GET", "/ping", context -> "pong"));
    }
}
