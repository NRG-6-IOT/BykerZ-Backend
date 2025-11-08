package nrg.inc.bykerz.vehicles.interfaces.rest.transform;

import nrg.inc.bykerz.vehicles.domain.model.commands.AddVehicleToOwnerCommand;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.AddVehicleResource;

public class AddVehicleCommandFromResourceAssembler {
    static AddVehicleToOwnerCommand toCommandFromResource(AddVehicleResource resource) {
        return new AddVehicleToOwnerCommand(
                resource.ownerId(),
                resource.modelId(),
                resource.year(),
                resource.plate()
        );
    }
}
