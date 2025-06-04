package co.com.jadat.usecase.persona;

import co.com.jadat.model.persona.Persona;
import co.com.jadat.model.persona.gateways.PersonaGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PersonaUseCase {

    private final PersonaGateway personaGateway;

    public Mono<Persona> savePersona(Persona persona){
        return personaGateway.save(persona);
    }

    public Mono<Persona> getPersonaById(Integer id){
        return personaGateway.findPersonaById(id);
    }

    public Mono<Persona> getPersonaByDocument(String document){
        return personaGateway.findPersonaByDocument(document);
    }

    public Flux<Persona> getAllPersonas(){
        return personaGateway.findAllPersonas();
    }

    public Mono<Void> deletePersona(Integer id){
        return personaGateway.deletePersonaByID(id);
    }
}
