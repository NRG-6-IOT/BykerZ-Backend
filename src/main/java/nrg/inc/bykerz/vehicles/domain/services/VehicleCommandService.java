package nrg.inc.bykerz.vehicles.domain.services;

import nrg.inc.bykerz.vehicles.domain.model.entities.Vehicle;
import nrg.inc.bykerz.vehicles.domain.model.commands.AddVehicleToOwnerCommand;
import nrg.inc.bykerz.vehicles.domain.model.commands.DeleteVehicleFromOwnerCommand;
import nrg.inc.bykerz.vehicles.domain.model.commands.UpdateVehicleCommand;


import java.util.Optional;

public interface VehicleCommandService {

    Optional<Vehicle> handle(AddVehicleToOwnerCommand command);

    Optional<Vehicle> handle(UpdateVehicleCommand command);

    void handle(DeleteVehicleFromOwnerCommand command);
}
