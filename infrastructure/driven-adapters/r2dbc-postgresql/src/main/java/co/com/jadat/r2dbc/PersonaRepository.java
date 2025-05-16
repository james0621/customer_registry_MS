package co.com.jadat.r2dbc;

import co.com.jadat.r2dbc.entity.PersonaEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PersonaRepository extends ReactiveCrudRepository<PersonaEntity, Integer>, ReactiveQueryByExampleExecutor<PersonaEntity> {

}
