package multi_tenant_back.tenant_api.PhoneNumbersTenant.Domain.Repository;
import multi_tenant_back.tenant_api.PhoneNumbersTenant.Domain.Model.PhoneNumbersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumbersRepository extends JpaRepository<PhoneNumbersModel, Long> {
}
