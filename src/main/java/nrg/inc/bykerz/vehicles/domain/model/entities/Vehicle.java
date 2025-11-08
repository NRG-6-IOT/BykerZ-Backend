package nrg.inc.bykerz.vehicles.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import nrg.inc.bykerz.shared.domain.model.entity.AuditableModel;
import nrg.inc.bykerz.vehicles.domain.model.aggregates.Model;
import nrg.inc.bykerz.vehicles.domain.model.aggregates.Owner;
import nrg.inc.bykerz.vehicles.domain.model.commands.UpdateVehicleFromOwnerCommand;
import nrg.inc.bykerz.vehicles.domain.model.valueobjects.Plate;
import nrg.inc.bykerz.vehicles.domain.model.valueobjects.Year;

@Getter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "plate")
})
public class Vehicle extends AuditableModel {

    @Setter
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @Embedded
    private Year year;

    @Embedded
    private Plate plate;

    protected Vehicle() {
        super();
    }

    public Vehicle(Model model, String year, String plate) {
        this.model = model;
        this.year = new Year(year);
        this.plate = new Plate(plate);
    }

    public Vehicle UpdateVehicle(UpdateVehicleFromOwnerCommand command) {
        this.plate = new Plate(command.plate());
        return this;
    }

}
