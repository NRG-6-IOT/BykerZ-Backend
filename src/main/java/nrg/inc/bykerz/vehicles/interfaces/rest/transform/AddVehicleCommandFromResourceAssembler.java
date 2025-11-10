package nrg.inc.bykerz.vehicles.interfaces.rest.transform;

import nrg.inc.bykerz.vehicles.domain.model.commands.AddVehicleToOwnerCommand;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.AddVehicleResource;

public class AddVehicleCommandFromResourceAssembler {
    public static AddVehicleToOwnerCommand toCommandFromResource(Long ownerId, AddVehicleResource resource) {
        return new AddVehicleToOwnerCommand(
                ownerId,
                resource.modelId(),
                resource.year(),
                resource.plate()
        );
    }
}
