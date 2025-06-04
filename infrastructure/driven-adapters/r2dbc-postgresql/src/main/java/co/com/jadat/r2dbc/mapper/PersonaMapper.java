package co.com.jadat.r2dbc.mapper;

import co.com.jadat.model.persona.Persona;
import co.com.jadat.r2dbc.entity.PersonaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonaMapper {

    PersonaMapper MAPPER = Mappers.getMapper(PersonaMapper.class);

    PersonaEntity domainToEntity(Persona persona);

    Persona entityToDomain(PersonaEntity personaEntity);
}
