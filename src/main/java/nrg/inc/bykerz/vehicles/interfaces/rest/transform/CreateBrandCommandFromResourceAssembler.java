package nrg.inc.bykerz.vehicles.interfaces.rest.transform;

import nrg.inc.bykerz.vehicles.domain.model.commands.CreateBrandCommand;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.CreateBrandResource;

public class CreateBrandCommandFromResourceAssembler {

    public static CreateBrandCommand toCommandFromResource(CreateBrandResource resource) {
        return new CreateBrandCommand(resource.brandName());
    }
}
