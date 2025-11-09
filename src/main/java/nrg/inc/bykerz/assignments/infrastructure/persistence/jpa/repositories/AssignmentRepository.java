package nrg.inc.bykerz.assignments.infrastructure.persistence.jpa.repositories;

import nrg.inc.bykerz.assignments.domain.model.aggregates.Assignment;
import nrg.inc.bykerz.assignments.domain.model.valueobjects.AssignmentCode;
import nrg.inc.bykerz.assignments.domain.model.valueobjects.AssignmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Optional<Assignment> findByOwnerIdAndStatus(Long ownerId, AssignmentStatus status);
    List<Assignment> findByMechanic_Id(Long mechanicId);
    boolean existsByAssignmentCode(AssignmentCode code);
    boolean existsByOwnerIdAndIdNotAndStatusNot(Long ownerId, Long id, AssignmentStatus status);
    Optional<Assignment> findByAssignmentCode(AssignmentCode code);
}
