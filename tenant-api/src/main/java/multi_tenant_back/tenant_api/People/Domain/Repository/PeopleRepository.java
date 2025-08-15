package multi_tenant_back.tenant_api.People.Domain.Repository;

import multi_tenant_back.tenant_api.People.Domain.Model.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PeopleRepository extends JpaRepository<People, UUID> {
    Optional<People> findFirstByCcOrPhoneNumbers(Long cc, Long phone);
}
