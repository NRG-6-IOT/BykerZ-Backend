package nrg.inc.bykerz.vehicle.infrastructure.persistence.jpa.repositories;

import nrg.inc.bykerz.vehicle.domain.model.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {



}
