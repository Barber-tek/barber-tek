package multi_tenant_back.tenant_api.Auth.Infrastructure;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserDTO {
    @NotBlank(message = "No fue proporcionado ningun email")
    private String email;
    @NotBlank(message = "No fue proporcionado ninguna contraseña")
    private String password;
    private UUID personId;
}
