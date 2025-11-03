package nrg.inc.bykerz.maintenance.domain.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import nrg.inc.bykerz.maintenance.domain.model.valueobjects.ExpenseTypes;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class ExpenseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    private ExpenseTypes name;

    public ExpenseType(ExpenseTypes name){
        this.name=name;
    }
}
