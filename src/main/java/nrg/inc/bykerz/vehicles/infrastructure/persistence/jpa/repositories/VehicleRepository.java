package nrg.inc.bykerz.vehicles.infrastructure.persistence.jpa.repositories;

import nrg.inc.bykerz.vehicles.domain.model.aggregates.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
