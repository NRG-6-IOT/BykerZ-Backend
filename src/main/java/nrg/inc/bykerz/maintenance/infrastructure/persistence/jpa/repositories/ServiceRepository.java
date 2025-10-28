package nrg.inc.bykerz.maintenance.infrastructure.persistence.jpa.repositories;

import nrg.inc.bykerz.maintenance.domain.model.agreggates.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {
}
