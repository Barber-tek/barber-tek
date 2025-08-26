package multi_tenant_back.tenant_api.People.Domain.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Person {
    @Id
    private UUID id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;

    private Long cc;
    private Long phoneNumbersModel;
}
