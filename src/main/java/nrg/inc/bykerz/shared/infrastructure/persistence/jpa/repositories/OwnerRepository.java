package nrg.inc.bykerz.shared.infrastructure.persistence.jpa.repositories;

import nrg.inc.bykerz.shared.domain.model.aggregates.Owner;
import nrg.inc.bykerz.shared.domain.model.valueobjects.OwnerCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    boolean existsByCode(OwnerCode code);
    Optional<Owner> findByCode(OwnerCode code);
}
