package nrg.inc.bykerz.vehicles.interfaces.acl;

import nrg.inc.bykerz.vehicles.domain.model.aggregates.Owner;
import nrg.inc.bykerz.vehicles.domain.model.entities.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehiclesContextFacade {

    Optional<Owner> fetchOwnerById(Long ownerId);

    List<Vehicle> fetchVehiclesByOwnerId(Long ownerId);

    Optional<Vehicle> fetchVehicleById(Long vehicleId);

}
