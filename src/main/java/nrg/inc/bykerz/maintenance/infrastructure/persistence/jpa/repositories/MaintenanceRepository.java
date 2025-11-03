package nrg.inc.bykerz.maintenance.infrastructure.persistence.jpa.repositories;

import nrg.inc.bykerz.maintenance.domain.model.agreggates.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance,Long> {
    List<Maintenance> getMaintenancesByUser_Id(Long userId);
}
