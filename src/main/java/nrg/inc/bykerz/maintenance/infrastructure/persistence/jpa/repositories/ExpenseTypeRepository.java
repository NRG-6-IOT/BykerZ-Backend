package nrg.inc.bykerz.maintenance.infrastructure.persistence.jpa.repositories;

import nrg.inc.bykerz.maintenance.domain.model.entities.ExpenseType;
import nrg.inc.bykerz.maintenance.domain.model.valueobjects.ExpenseTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseTypeRepository extends JpaRepository<ExpenseType,Long> {
    Boolean existsByName(ExpenseTypes name);
}
