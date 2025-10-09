package nrg.inc.bykerz.vehicle.domain.services;

import nrg.inc.bykerz.vehicle.domain.model.aggregates.Vehicle;
import nrg.inc.bykerz.vehicle.domain.model.commands.CreateVehicleCommand;


import java.util.Optional;

public interface VehicleCommandService {

    Optional<Vehicle> handle(CreateVehicleCommand createVehicleCommand);

}
