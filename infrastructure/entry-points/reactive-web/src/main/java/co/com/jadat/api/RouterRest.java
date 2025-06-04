package co.com.jadat.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {

    private static final String PATH = "/api/v1/personas";

    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(POST(PATH), handler::save)
                .andRoute(GET(PATH), handler::getAll)
                .andRoute(GET(PATH.concat("/{id}")), handler::getById)
                .andRoute(GET(PATH.concat("/document/{idNumber}")), handler::getByDocument)
                .andRoute(DELETE(PATH.concat("/{id}")), handler::deleteById);
    }
}
