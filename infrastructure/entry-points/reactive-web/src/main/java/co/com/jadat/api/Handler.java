package co.com.jadat.api;

import co.com.jadat.api.dto.request.PersonaRequest;
import co.com.jadat.api.mapper.PersonaHandlerMapper;
import co.com.jadat.usecase.persona.PersonaUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class Handler {

    private final PersonaUseCase personaUseCase;


    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(PersonaRequest.class)
                .doOnNext(result -> log.info("Request Save Persona: {}", result))
                .map(PersonaHandlerMapper.MAPPER::requestToDomain)
                .doOnNext(mapPersona -> log.info("Map Persona to Domain: {}", mapPersona))
                .flatMap(personaUseCase::savePersona)
                .doOnError(error -> log.error("Error Save Persona: {}", error.getMessage()))
                .map(PersonaHandlerMapper.MAPPER::domainToResponse)
                .flatMap(personaResponse -> ServerResponse.ok().bodyValue(personaResponse));
    }


    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        return Mono.just(Integer.parseInt(serverRequest.pathVariable("id")))
                .doOnNext(request -> log.info("Persona By Id Request: {}", request))
                .flatMap(personaUseCase::getPersonaById)
                .map(PersonaHandlerMapper.MAPPER::domainToResponse)
                .doOnNext(response -> log.info("Persona By Id Response: {}", response))
                .flatMap(personaResponse -> ServerResponse.ok().bodyValue(personaResponse))
                .doOnError(error -> log.error("Error Get Persona By Id: {}", error.getMessage()));
    }


    public Mono<ServerResponse> getByDocument(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("idNumber"))
                .doOnNext(request -> log.info("Persona By Document Request: {}", request))
                .flatMap(personaUseCase::getPersonaByDocument)
                .map(PersonaHandlerMapper.MAPPER::domainToResponse)
                .doOnNext(response -> log.info("Persona By Document Response: {}", response))
                .flatMap(personaResponse -> ServerResponse.ok().bodyValue(personaResponse))
                .doOnError(error -> log.error("Persona By Document Error: {}", error.getMessage()));
    }

    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
        return personaUseCase.getAllPersonas()
                .map(PersonaHandlerMapper.MAPPER::domainToResponse)
                .collectList()
                .flatMap(personaResponse -> ServerResponse.ok().bodyValue(personaResponse));
    }

    public Mono<ServerResponse> deleteById(ServerRequest serverRequest){
        return Mono.just(Integer.parseInt(serverRequest.pathVariable("id")))
                .flatMap(personaUseCase::deletePersona)
                .flatMap(response -> ServerResponse.ok().bodyValue("One Persona Has Deleted"));
    }
}
