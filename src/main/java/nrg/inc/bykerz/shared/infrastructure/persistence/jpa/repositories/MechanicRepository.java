package nrg.inc.bykerz.shared.infrastructure.persistence.jpa.repositories;

import nrg.inc.bykerz.shared.domain.model.aggregates.Mechanic;
import nrg.inc.bykerz.shared.domain.model.valueobjects.MechanicCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Long> {
    boolean existsByCode(MechanicCode code);
    Optional<Mechanic> findByCode(MechanicCode code);
}
