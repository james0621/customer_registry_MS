package co.com.jadat.r2dbc.repository;

import co.com.jadat.r2dbc.entity.PersonaEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PersonaRepository extends ReactiveCrudRepository<PersonaEntity, String>, ReactiveQueryByExampleExecutor<PersonaEntity> {

    Mono<PersonaEntity> findByDocumentNumber(String documentNumber);
}
