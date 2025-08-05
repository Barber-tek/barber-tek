package multi_tenant_back.tenant_api.CitiesStates.Domain.Repository;

import multi_tenant_back.tenant_api.CitiesStates.Domain.Model.StateModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatesRepository extends JpaRepository<StateModel, Integer> {
}
