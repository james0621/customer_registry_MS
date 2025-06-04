package co.com.jadat.r2dbc.service;

import co.com.jadat.model.persona.Persona;
import co.com.jadat.model.persona.gateways.PersonaGateway;
import co.com.jadat.r2dbc.mapper.PersonaMapper;
import co.com.jadat.r2dbc.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonaGatewayImpl implements PersonaGateway {

    private final PersonaRepository personaRepository;

    @Override
    public Mono<Persona> save(Persona persona) {
        return personaRepository.save(PersonaMapper.MAPPER.domainToEntity(persona))
                .doOnSubscribe(subscription -> log.info("Persona Save Request R2DBC", kv("PersonaRQ", persona)))
                .doOnSuccess(personaEntity -> log.info("Persona Save Response", kv("PersonaRS", personaEntity)))
                .map(PersonaMapper.MAPPER::entityToDomain)
                .doOnError(error -> log.error("Error Save Persona", kv("Error", error.getMessage())));
    }

    @Override
    public Mono<Persona> findPersonaById(Integer id) {
        return personaRepository.findById(String.valueOf(id))
                .doOnSubscribe(subscription -> log.info("Persona Find By Id RQ", kv("PersonaRQ", id)))
                .doOnSuccess(personaEntity -> log.info("Persona Find By Id RS", kv("PersonaRS", personaEntity)))
                .map(PersonaMapper.MAPPER::entityToDomain)
                .doOnError(error -> log.error("Error Find By Id Persona", kv("Error", error.getMessage())));
    }

    @Override
    public Mono<Persona> findPersonaByDocument(String document) {
        return personaRepository.findByDocumentNumber(document)
                .doOnSubscribe(subscription -> log.info("Persona Find By Document Number", kv("PersonaRQ", document)))
                .doOnSuccess(personaEntity -> log.info("Persona Find By Document Number", kv("PersonaRS", personaEntity)))
                .map(PersonaMapper.MAPPER::entityToDomain)
                .doOnError(error -> log.error("Error Find By Document Number Persona", kv("Error", error.getMessage())));
    }

    @Override
    public Flux<Persona> findAllPersonas() {
        return personaRepository.findAll()
                .map(PersonaMapper.MAPPER::entityToDomain)
                .doOnComplete(() -> log.info("Se obtuvieron todos los clientes"))
                .doOnError(error -> log.error("Error Find All Persons: {}", error.getMessage()));
    }

    @Override
    public Mono<Void> deletePersonaByID(Integer id) {
        return personaRepository.deleteById(String.valueOf(id))
                .doOnSuccess(success -> log.info("Se elimino correctamente el usuario con id: {}", id))
                .doOnError(error -> log.error("Error al eliminar persona: {}", error.getMessage()));
    }
}
