package multi_tenant_back.tenant_api.Roles.infrastructure;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import multi_tenant_back.tenant_api.Roles.Domain.Model.RolesModel;
import multi_tenant_back.tenant_api.Roles.Domain.Service.RolesService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SeederRoles {
    private final RolesService rolesService;

    @PostConstruct
    public void seedRoles() {
        try{
            List<String> rolesNames = Arrays.stream(RolesEnum.values()).map(Enum::name).toList();
            List<RolesModel> roles = rolesService.createRol(rolesNames);
            System.out.println("Roles created " + roles.size() + " roles");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
