package nrg.inc.bykerz.report.infrastructure.persistence.jpa.repositories;

import nrg.inc.bykerz.report.domain.model.entities.MetricType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricTypeRepository extends JpaRepository<MetricType, Long> {
}
