package nrg.inc.bykerz.maintenance.domain.model.agreggates;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import nrg.inc.bykerz.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

public class Expense extends AuditableAbstractAggregateRoot<Expense> {
    @NotNull
    private String name;

    @NotNull
    private Double finalPrice;


    private Long userId;


}
