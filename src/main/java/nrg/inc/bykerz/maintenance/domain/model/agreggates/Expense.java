package nrg.inc.bykerz.maintenance.domain.model.agreggates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nrg.inc.bykerz.iam.domain.model.aggregates.User;
import nrg.inc.bykerz.maintenance.domain.model.entities.ExpenseType;
import nrg.inc.bykerz.maintenance.domain.model.entities.ItemType;
import nrg.inc.bykerz.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Expense extends AuditableAbstractAggregateRoot<Expense> {
    @NotNull
    private String name;

    @NotNull
    private Double finalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExpenseItem> expenseItems;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "expense_type_id")
    private ExpenseType expenseType;

    public Expense(String name, Double finalPrice, User user, ExpenseType expenseType) {
        this.name = name;
        this.finalPrice = finalPrice;
        this.user = user;
        this.expenseType = expenseType;
        this.expenseItems = new ArrayList<>();
    }



}
