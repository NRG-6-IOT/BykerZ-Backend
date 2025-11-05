package nrg.inc.bykerz.vehicles.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import nrg.inc.bykerz.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import nrg.inc.bykerz.vehicles.domain.model.commands.UpdateVehicleCommand;
import nrg.inc.bykerz.vehicles.domain.model.entities.Model;
import nrg.inc.bykerz.vehicles.domain.model.valueobjects.Plate;
import nrg.inc.bykerz.vehicles.domain.model.valueobjects.Year;

@Getter
@Entity
public class Vehicle extends AuditableAbstractAggregateRoot<Vehicle> {

    private Long ownerId;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @Embedded
    private Year year;

    @Embedded
    private Plate plate;

    protected Vehicle() {
        super();
    }

    public Vehicle(Long ownerId, Model model, String year, String plate) {
        this.ownerId = ownerId;
        this.model = model;
        this.year = new Year(year);
        this.plate = new Plate(plate);
    }

    public Vehicle UpdateVehicle(UpdateVehicleCommand command) {
        this.plate = new Plate(command.plate());
        return this;
    }



}
