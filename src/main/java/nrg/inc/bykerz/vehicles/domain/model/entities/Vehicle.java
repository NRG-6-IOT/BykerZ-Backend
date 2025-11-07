package nrg.inc.bykerz.vehicles.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import nrg.inc.bykerz.shared.domain.model.entity.AuditableModel;
import nrg.inc.bykerz.vehicles.domain.model.aggregates.Model;
import nrg.inc.bykerz.vehicles.domain.model.aggregates.Owner;
import nrg.inc.bykerz.vehicles.domain.model.commands.UpdateVehicleCommand;
import nrg.inc.bykerz.vehicles.domain.model.commands.UpdateVehicleFromOwnerCommand;
import nrg.inc.bykerz.vehicles.domain.model.valueobjects.Plate;
import nrg.inc.bykerz.vehicles.domain.model.valueobjects.Year;

@Getter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "plate")
})
public class Vehicle extends AuditableModel {

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    private Long modelId;

    @Embedded
    private Year year;

    @Embedded
    private Plate plate;

    protected Vehicle() {
        super();
    }

    public Vehicle(Owner owner, Long modelId, String year, String plate) {
        this.owner = owner;
        this.modelId = modelId;
        this.year = new Year(year);
        this.plate = new Plate(plate);
    }

    public Vehicle UpdateVehicle(UpdateVehicleFromOwnerCommand command) {
        this.plate = new Plate(command.plate());
        return this;
    }



}
