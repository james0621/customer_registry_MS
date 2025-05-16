package co.com.jadat.model.persona;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Persona {

    private Integer id;
    private String documentNumber;
    private String name;
    private String lastName;
    private String email;
}
