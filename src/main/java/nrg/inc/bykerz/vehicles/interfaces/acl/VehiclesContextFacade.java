package nrg.inc.bykerz.vehicles.interfaces.acl;

import nrg.inc.bykerz.vehicles.domain.model.aggregates.Vehicle;

import java.util.Optional;

public interface VehiclesContextFacade {
    Optional<Vehicle> fetchVehicleById(Long vehicleId);
}
