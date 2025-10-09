package nrg.inc.bykerz.vehicle.interfaces.rest.transform;

import nrg.inc.bykerz.vehicle.domain.model.commands.CreateVehicleCommand;
import nrg.inc.bykerz.vehicle.interfaces.rest.resources.CreateVehicleResource;

public class CreateVehicleCommandFromResourceAssembler {

    public static CreateVehicleCommand toCommandFromResource(CreateVehicleResource resource) {
        return new CreateVehicleCommand(
                resource.mechanicId(),
                resource.modelId(), // Model ya existente
                resource.manufacturingDate(),
                resource.plate()
        );
    }
}
