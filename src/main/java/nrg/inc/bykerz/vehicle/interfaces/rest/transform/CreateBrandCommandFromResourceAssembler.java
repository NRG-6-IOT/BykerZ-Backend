package nrg.inc.bykerz.vehicle.interfaces.rest.transform;

import nrg.inc.bykerz.vehicle.domain.model.commands.CreateBrandCommand;
import nrg.inc.bykerz.vehicle.interfaces.rest.resources.CreateBrandResource;

public class CreateBrandCommandFromResourceAssembler {

    public static CreateBrandCommand toCommandFromResource(CreateBrandResource resource) {
        return new CreateBrandCommand(resource.brandName());
    }
}
