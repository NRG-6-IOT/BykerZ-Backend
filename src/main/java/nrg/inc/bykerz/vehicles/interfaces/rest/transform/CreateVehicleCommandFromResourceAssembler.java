package nrg.inc.bykerz.vehicles.interfaces.rest.transform;

import nrg.inc.bykerz.vehicles.domain.model.commands.AddVehicleToOwnerCommand;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.CreateVehicleResource;

public class CreateVehicleCommandFromResourceAssembler {

    public static AddVehicleToOwnerCommand toCommandFromResource(CreateVehicleResource resource, Long ownerId) {
        return new AddVehicleToOwnerCommand(
                ownerId,
                resource.modelId(),
                resource.year(),
                resource.plate()
        );
    }
}
