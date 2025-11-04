package nrg.inc.bykerz.vehicles.interfaces.rest.transform;

import nrg.inc.bykerz.vehicles.domain.model.commands.CreateVehicleCommand;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.CreateVehicleResource;

public class CreateVehicleCommandFromResourceAssembler {

    public static CreateVehicleCommand toCommandFromResource(CreateVehicleResource resource, Long ownerId) {
        return new CreateVehicleCommand(
                ownerId,
                resource.modelId(),
                resource.year(),
                resource.plate()
        );
    }
}
