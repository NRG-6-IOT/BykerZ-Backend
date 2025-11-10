package nrg.inc.bykerz.vehicles.interfaces.rest.transform;

import nrg.inc.bykerz.vehicles.domain.model.commands.CreateOwnerCommand;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.CreateOwnerResource;

public class CreateOwnerCommandFromResourceAssembler {
    public static CreateOwnerCommand toCommandFromResource(CreateOwnerResource resource) {
        return new CreateOwnerCommand(
                resource.profileId()
        );
    }
}
