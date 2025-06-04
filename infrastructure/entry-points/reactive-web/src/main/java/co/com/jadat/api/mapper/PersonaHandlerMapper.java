package co.com.jadat.api.mapper;

import co.com.jadat.api.dto.request.PersonaRequest;
import co.com.jadat.api.dto.response.PersonaResponse;
import co.com.jadat.model.persona.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonaHandlerMapper {

    PersonaHandlerMapper MAPPER = Mappers.getMapper(PersonaHandlerMapper.class);

    @Mapping(target = "documentNumber", source = "personaRequest.personaRQ.documentNumber")
    @Mapping(target = "name", source = "personaRequest.personaRQ.name")
    @Mapping(target = "lastName", source = "personaRequest.personaRQ.lastName")
    @Mapping(target = "email", source = "personaRequest.personaRQ.email")
    Persona requestToDomain(PersonaRequest personaRequest);

    @Mapping(target = "personaRS.id", source = "persona.id")
    @Mapping(target = "personaRS.name", source = "persona.name")
    @Mapping(target = "personaRS.lastName", source = "persona.lastName")
    @Mapping(target = "personaRS.email", source = "persona.email")
    PersonaResponse domainToResponse(Persona persona);
}
