package nrg.inc.bykerz.vehicles.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import nrg.inc.bykerz.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import nrg.inc.bykerz.vehicles.domain.model.commands.CreateVehicleCommand;
import nrg.inc.bykerz.vehicles.domain.model.commands.UpdateVehicleCommand;
import nrg.inc.bykerz.vehicles.domain.model.entities.Model;

import java.util.Date;

@Getter
@Entity
public class Vehicle extends AuditableAbstractAggregateRoot<Vehicle> {

    private Long ownerId;
    private Long mechanicId;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    private String year;
    private String plate;

    protected Vehicle() {
        super();
    }

    public Vehicle(Long ownerId, Long mechanicId, Model model, String year, String plate) {
        this.ownerId = ownerId;
        this.mechanicId = mechanicId;
        this.model = model;
        this.year = year;
        this.plate = plate;
    }

    public void UpdateVehicle(UpdateVehicleCommand command) {
        this.mechanicId = command.mechanicId();
        this.plate = command.plate();
    }



}
