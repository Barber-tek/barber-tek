package multi_tenant_back.tenant_api.CitiesStates.Domain.Repository;

import multi_tenant_back.tenant_api.CitiesStates.Domain.Model.CitiesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitiesRepository extends JpaRepository<CitiesModel,Long> {
}
