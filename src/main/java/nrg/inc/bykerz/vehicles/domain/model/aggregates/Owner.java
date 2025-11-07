package nrg.inc.bykerz.vehicles.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import nrg.inc.bykerz.profiles.domain.model.aggregates.Profile;
import nrg.inc.bykerz.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
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

    public void AddVehicle(Long modelId, String year, String plate) {
        this.vehicles.add(new Vehicle(this, modelId, year, plate));
    }

    public List<Vehicle> GetVehicles() { return vehicles; }

    public void DeleteVehicle(Long vehicleId) {
        this.vehicles.remove(this.vehicles.stream().filter(v -> v.getId().equals(vehicleId)).findFirst().orElse(null));
    }
}