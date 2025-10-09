package nrg.inc.bykerz.vehicle.domain.model.aggregates;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import nrg.inc.bykerz.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import nrg.inc.bykerz.vehicle.domain.model.commands.CreateVehicleCommand;
import nrg.inc.bykerz.vehicle.domain.model.entities.Model;

import java.util.Date;

@Getter
@Entity
public class Vehicle extends AuditableAbstractAggregateRoot<Vehicle> {

    private Long mechanicId;

    @OneToOne(cascade = CascadeType.ALL)
    private Model model;

    private Date manufacturingDate;
    private String plate;

    protected Vehicle() {
        super();
    }

    public Vehicle( Long mechanicId,Model model, Date manufacturingDate, String plate) {

        this.mechanicId = mechanicId;
        this.model = model;
        this.manufacturingDate = manufacturingDate;
        this.plate = plate;
    }

    public Vehicle(CreateVehicleCommand command, Model model) {
        this.mechanicId = command.mechanicId();
        this.model = model; // ya existe en la BD
        this.manufacturingDate = command.manufacturingDate();
        this.plate = command.plate();
    }



}
