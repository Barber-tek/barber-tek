package multi_tenant_back.tenant_api.Roles.Domain.Service;

import lombok.RequiredArgsConstructor;
import multi_tenant_back.tenant_api.Roles.Domain.Model.RolesModel;
import multi_tenant_back.tenant_api.Roles.Domain.Repository.RolesRepository;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolesService {
    private final RolesRepository rolesRepository;
    public List<RolesModel> createRol(List<String> rolesName) throws InvalidParameterException{
        if(!rolesRepository.findAll().isEmpty()){
            throw new InvalidParameterException("Roles already exist");
        }
        if(rolesName.isEmpty()){
            throw new InvalidParameterException("Not enough roles names");
        }
        List<RolesModel> roles = rolesName.stream().map(item->{
            RolesModel role = new RolesModel();
            role.setName(item);
            return role;
        }).toList();

        return rolesRepository.saveAll(roles);
    }

    public RolesModel findByName(String roleName) throws InvalidParameterException{
        return rolesRepository.findByName(roleName).orElseThrow();
    }
}
