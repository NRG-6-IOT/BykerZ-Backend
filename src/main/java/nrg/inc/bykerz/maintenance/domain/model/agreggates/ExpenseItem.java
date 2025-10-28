package nrg.inc.bykerz.maintenance.domain.model.agreggates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nrg.inc.bykerz.maintenance.domain.model.entities.ItemType;
import nrg.inc.bykerz.shared.domain.model.entity.AuditableModel;

import java.util.Set;

@Getter
@Entity
@NoArgsConstructor
public class ExpenseItem extends AuditableModel {

    @NotNull
    private String name;

    @NotNull
    private Integer amount;

    @NotNull
    private Double unitPrice;

    @NotNull
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "item_type_id")
    private ItemType itemType;

}
