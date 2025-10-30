package nrg.inc.bykerz.maintenance.domain.model.agreggates;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nrg.inc.bykerz.shared.domain.model.entity.AuditableModel;
import nrg.inc.bykerz.vehicle.domain.model.aggregates.Vehicle;

import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
public class Maintenance extends AuditableModel {

    @NotNull
    private String details;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @NotNull
    private Date dateOfService;

    @NotNull
    private String location;

    @NotNull
    private String description;

    @ManyToOne
    @JoinColumn(name = "maintenance_expense_id")
    private Expense maintenanceExpense;


}
