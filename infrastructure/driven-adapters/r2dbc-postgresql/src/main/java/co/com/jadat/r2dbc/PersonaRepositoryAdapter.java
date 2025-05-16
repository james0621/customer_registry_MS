package co.com.jadat.r2dbc;

import co.com.jadat.model.persona.Persona;
import co.com.jadat.r2dbc.entity.PersonaEntity;
import co.com.jadat.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaRepositoryAdapter extends ReactiveAdapterOperations<
        Persona/* change for domain model */,
        PersonaEntity/* change for adapter model */,
        Integer,
        PersonaRepository
> {
    public PersonaRepositoryAdapter(PersonaRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Persona.class/* change for domain model */));
    }

}
