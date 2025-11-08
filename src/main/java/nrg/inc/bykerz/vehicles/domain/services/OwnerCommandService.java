package nrg.inc.bykerz.vehicles.domain.services;

import nrg.inc.bykerz.vehicles.domain.model.aggregates.Owner;
import nrg.inc.bykerz.vehicles.domain.model.commands.AddVehicleToOwnerCommand;
import nrg.inc.bykerz.vehicles.domain.model.commands.CreateOwnerCommand;
import nrg.inc.bykerz.vehicles.domain.model.commands.DeleteVehicleFromOwnerCommand;
import nrg.inc.bykerz.vehicles.domain.model.commands.UpdateVehicleFromOwnerCommand;
import nrg.inc.bykerz.vehicles.domain.model.entities.Vehicle;

import java.util.List;
import java.util.Optional;

public interface OwnerCommandService {
    Optional<Owner> handle(CreateOwnerCommand command);

    Optional<Vehicle> handle(AddVehicleToOwnerCommand command);

    Optional<Vehicle> handle(UpdateVehicleFromOwnerCommand command);

    void handle(DeleteVehicleFromOwnerCommand command);
}
