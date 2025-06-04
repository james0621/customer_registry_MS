package co.com.jadat.r2dbc.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("personas")
public class PersonaEntity {

    @Id
    private Integer id;

    @Column("document_number")
    private String documentNumber;

    private String name;

    @Column("last_name")
    private String lastName;

    private String email;
}
