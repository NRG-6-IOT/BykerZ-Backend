package nrg.inc.bykerz.vehicles.infrastructure.persistence.jpa.repositories;

import nrg.inc.bykerz.vehicles.domain.model.aggregates.Vehicle;
import nrg.inc.bykerz.vehicles.domain.model.valueobjects.Plate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    boolean existsByPlate(Plate plate);
    Optional<Vehicle> findByPlate(Plate plate);
    List<Vehicle> findByOwnerId(Long ownerId);
}