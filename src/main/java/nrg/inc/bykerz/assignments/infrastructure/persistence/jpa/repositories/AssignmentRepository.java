package nrg.inc.bykerz.assignments.infrastructure.persistence.jpa.repositories;

import nrg.inc.bykerz.assignments.domain.model.aggregates.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Optional<Assignment> findByVehicleId(Long vehicleId);

    List<Assignment> findByMechanicId(Long mechanicId);
}
