package nrg.inc.bykerz.maintenance.domain.model.agreggates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import nrg.inc.bykerz.maintenance.domain.model.entities.ItemType;
import nrg.inc.bykerz.shared.domain.model.entity.AuditableModel;

import java.util.Set;

public class ExpenseItem extends AuditableModel {

    @NotNull
    private String name;

    @NotNull
    private Integer amount;

    @NotNull
    private Double unitPrice;

    @NotNull
    private Double totalPrice;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "item_types",
            joinColumns = @JoinColumn(name="expense_item_id"),
            inverseJoinColumns = @JoinColumn(name="item_type_id")
    )
    private Set<ItemType> itemTypes;
}
