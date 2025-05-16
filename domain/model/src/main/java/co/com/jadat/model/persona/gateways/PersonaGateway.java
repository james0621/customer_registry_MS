package co.com.jadat.model.persona.gateways;

import co.com.jadat.model.persona.Persona;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonaGateway {

    Mono<Persona> save(Persona persona);
    Mono<Persona> findPersonaById(Integer id);
    Mono<Persona> findPersonaByDocument(String document);
    Flux<Persona> findAllPersonas();
    Mono<Void> deletePersonaByID(Integer id);
}
