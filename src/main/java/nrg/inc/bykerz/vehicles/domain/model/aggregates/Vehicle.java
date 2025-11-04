package nrg.inc.bykerz.vehicles.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import nrg.inc.bykerz.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import nrg.inc.bykerz.vehicles.domain.model.commands.UpdateVehicleCommand;
import nrg.inc.bykerz.vehicles.domain.model.entities.Model;

@Getter
@Entity
public class Vehicle extends AuditableAbstractAggregateRoot<Vehicle> {

    private Long ownerId;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    private String year;
    private String plate;

    protected Vehicle() {
        super();
    }

    public Vehicle(Long ownerId, Model model, String year, String plate) {
        this.ownerId = ownerId;
        this.model = model;
        this.year = year;
        this.plate = plate;
    }

    public Vehicle UpdateVehicle(UpdateVehicleCommand command) {
        this.plate = command.plate();
        return this;
    }



}
