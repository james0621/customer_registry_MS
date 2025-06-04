package co.com.jadat.model.persona;

import lombok.Value;
import lombok.Builder;


@Value
@Builder(toBuilder = true)
public class Persona {

    Integer id;
    String documentNumber;
    String name;
    String lastName;
    String email;
}
