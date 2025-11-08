package nrg.inc.bykerz.assignments.infrastructure.persistence.jpa.repositories;

import nrg.inc.bykerz.assignments.domain.model.aggregates.Mechanic;
import nrg.inc.bykerz.assignments.domain.model.valueobjects.MechanicCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Long> {
    boolean existsByCode(MechanicCode code);
    Optional<Mechanic> findByCode(MechanicCode code);
}
