package nrg.inc.bykerz.shared.infrastructure.persistence.jpa.repositories;

import nrg.inc.bykerz.shared.domain.model.aggregates.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
