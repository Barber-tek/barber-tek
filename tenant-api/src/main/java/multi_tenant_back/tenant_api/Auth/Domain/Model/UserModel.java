package multi_tenant_back.tenant_api.Auth.Domain.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import multi_tenant_back.tenant_api.Auth.Infrastructure.UserDTO;
import multi_tenant_back.tenant_api.People.Domain.Model.People;

import java.util.UUID;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "people_id")
    private People people;

    public UserModel (UserDTO userDTO, People people) {
        this.email = userDTO.getEmail();
        this.people = people;
    }

}
