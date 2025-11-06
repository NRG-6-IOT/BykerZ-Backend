package nrg.inc.bykerz.maintenance.interfaces.rest.transform;

import nrg.inc.bykerz.maintenance.domain.model.commands.CreateMaintenanceCommand;
import nrg.inc.bykerz.maintenance.interfaces.rest.resources.CreateMaintenanceResource;

public class CreateMaintenanceCommandFromResourceAssembler {
    public static CreateMaintenanceCommand toCommandFromResource(CreateMaintenanceResource resource) {
        return new CreateMaintenanceCommand(
                resource.details(),
                resource.vehicleId(),
                resource.dateOfService(),
                resource.location(),
                resource.description(),
                resource.mechanicId()
        );
    }
}
