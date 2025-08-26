package multi_tenant_back.tenant_api.Roles.Domain.Repository;

import multi_tenant_back.tenant_api.Roles.Domain.Model.RolesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<RolesModel, Integer> {
    Optional<RolesModel> findByName(String name);
}
