package multi_tenant_back.tenant_api.People.Infrastructure;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeopleDTO {
    @NotBlank(message = "Falto primer nombre")
    private String firstName;
    private String middleName;
    @NotBlank(message = "Falto primer apellido")
    private String lastName;
    private String secondLastName;
    @NotNull(message = "No se proporciono la cedula de la persona")
    private Long cc;
    @NotNull(message = "No se proporciono el telefono de la persona")
    private Long phoneNumbers;
}