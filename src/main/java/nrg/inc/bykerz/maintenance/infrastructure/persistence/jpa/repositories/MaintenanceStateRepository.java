package nrg.inc.bykerz.maintenance.infrastructure.persistence.jpa.repositories;

import nrg.inc.bykerz.maintenance.domain.model.entities.MaintenanceState;
import nrg.inc.bykerz.maintenance.domain.model.valueobjects.MaintenanceStates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceStateRepository extends JpaRepository<MaintenanceState,Long> {
    boolean existsByName(MaintenanceStates name);
}
