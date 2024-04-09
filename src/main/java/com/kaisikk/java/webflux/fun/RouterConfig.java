package com.kaisikk.java.webflux.fun;

import com.kaisikk.java.webflux.domain.Language;
import com.kaisikk.java.webflux.repo.LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Configuration
public class RouterConfig {

    @Autowired
    private LanguageRepo languageRepo;

    @Bean
    public RouterFunction<?> routerFunction() {
        return
                route(GET("/lan"), this::getAllLanguages)
                        .andRoute(GET("/lan/{name}"), request ->
                                ok().body(languageRepo.findByName(request.pathVariable("name")), Language.class))
                        .andRoute(POST("/lan"), this::postLanguage)
                        .andRoute(DELETE("/lan/{name}"),
                                request -> languageRepo.findByName(request.pathVariable("name"))
                                        .flatMap(i -> noContent().build(languageRepo.delete(i)))
                                        .switchIfEmpty(notFound().build()));
    }

    private Mono<ServerResponse> postLanguage(ServerRequest serverRequest) {
        Mono<Language> languageMonoPost = serverRequest.bodyToMono(Language.class);
        Mono<Language> languageMonoSave = languageRepo.saveAll(languageMonoPost).next();
        return ServerResponse.ok().body(languageMonoSave, Language.class);
    }

    private Mono<ServerResponse> getAllLanguages(ServerRequest request) {
        return ServerResponse.ok().body(languageRepo.findAll(), Language.class);
    }

}
