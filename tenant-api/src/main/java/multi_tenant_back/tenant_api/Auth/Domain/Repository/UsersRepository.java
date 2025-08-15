package multi_tenant_back.tenant_api.Auth.Domain.Repository;

import multi_tenant_back.tenant_api.Auth.Domain.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByEmail(String emailAddress);
}
