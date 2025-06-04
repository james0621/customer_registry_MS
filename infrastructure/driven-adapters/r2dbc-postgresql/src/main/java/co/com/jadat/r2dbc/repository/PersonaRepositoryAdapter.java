package co.com.jadat.r2dbc.repository;

import co.com.jadat.model.persona.Persona;
import co.com.jadat.r2dbc.entity.PersonaEntity;
import co.com.jadat.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaRepositoryAdapter extends ReactiveAdapterOperations<
        Persona/* change for domain model */,
        PersonaEntity/* change for adapter model */,
        String,
        PersonaRepository
> {
    public PersonaRepositoryAdapter(PersonaRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Persona.class/* change for domain model */));
    }

}
