package nrg.inc.bykerz.vehicles.interfaces.rest.transform;

import nrg.inc.bykerz.vehicles.domain.model.commands.CreateVehicleCommand;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.CreateVehicleResource;

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
