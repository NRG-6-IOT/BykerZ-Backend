package nrg.inc.bykerz.maintenance.domain.model.agreggates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nrg.inc.bykerz.maintenance.domain.model.entities.MaintenanceState;
import nrg.inc.bykerz.maintenance.domain.model.valueobjects.MaintenanceStates;
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
    @JoinColumn(name = "maintenance_expense_id", nullable = true)
    private Expense maintenanceExpense;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "maintenance_state_id")
    private MaintenanceState state;

    public Maintenance(
            String details,
            Vehicle vehicle,
            Date dateOfService,
            String location,
            String description,
            MaintenanceState state
    ) {
        this.details = details;
        this.vehicle = vehicle;
        this.dateOfService = dateOfService;
        this.location = location;
        this.description = description;
        this.state = state;
    }

    public void AssignExpenseToMaintenance(Expense expense){
        this.maintenanceExpense = expense;
    }

    public void UpdateStateOfMaintenance(MaintenanceState newState){
        this.state = newState;
    }


}
