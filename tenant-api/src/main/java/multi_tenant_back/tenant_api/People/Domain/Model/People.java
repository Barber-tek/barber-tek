package multi_tenant_back.tenant_api.People.Domain.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import multi_tenant_back.tenant_api.People.Infrastructure.PeopleDTO;
import multi_tenant_back.tenant_api.Roles.Domain.Model.RolesModel;

import java.util.UUID;

@Entity
@Table(name = "people")
@Getter
@Setter
@NoArgsConstructor
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    @Column(nullable = true)
    private String secondName;
    private String lastName;
    @Column(nullable = true)
    private String secondLastName;
    @Column(nullable = true, unique = true)
    private Long cc;
    @Column(nullable = true, unique = true)
    private Long phoneNumbers;

    public People(PeopleDTO dto) {
        this.firstName = dto.getFirstName();
        this.secondName = dto.getMiddleName();
        this.lastName = dto.getLastName();
        this.secondLastName = dto.getSecondLastName();
        this.cc = dto.getCc();
        this.phoneNumbers = dto.getPhoneNumbers();
    }
}
