package nrg.inc.bykerz.vehicles.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import nrg.inc.bykerz.profiles.domain.model.aggregates.Profile;
import nrg.inc.bykerz.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import nrg.inc.bykerz.vehicles.domain.model.commands.UpdateVehicleFromOwnerCommand;
import nrg.inc.bykerz.vehicles.domain.model.entities.Vehicle;

import java.util.List;

@Entity
@Getter
public class Owner extends AuditableAbstractAggregateRoot<Owner> {

    private Long profileId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles;

    protected Owner() {
    }

    public Owner(Long profileId) {
        this.profileId = profileId;
    }

    public Vehicle AddVehicle(Long modelId, String year, String plate) {
        var vehicle = new Vehicle(this, modelId, year, plate);
        this.vehicles.add(vehicle);
        return vehicle;
    }

    public List<Vehicle> GetVehicles() { return vehicles; }

    public void DeleteVehicle(Long vehicleId) {
        this.vehicles.remove(this.vehicles.stream().filter(v -> v.getId().equals(vehicleId)).findFirst().orElse(null));
    }

    public Vehicle UpdateVehicle(UpdateVehicleFromOwnerCommand command) {
        var opt = this.vehicles.stream()
                .filter(v -> v.getId().equals(command.vehicleId()))
                .findFirst();

        if (opt.isPresent()) {
            var vehicle = opt.get();
            vehicle.UpdateVehicle(command);
            return vehicle;
        }

        return null;
    }
}