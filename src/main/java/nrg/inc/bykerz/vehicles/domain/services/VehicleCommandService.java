package nrg.inc.bykerz.vehicles.domain.services;

import nrg.inc.bykerz.vehicles.domain.model.aggregates.Vehicle;
import nrg.inc.bykerz.vehicles.domain.model.commands.CreateVehicleCommand;


import java.util.Optional;

public interface VehicleCommandService {

    Optional<Vehicle> handle(CreateVehicleCommand createVehicleCommand);

}
