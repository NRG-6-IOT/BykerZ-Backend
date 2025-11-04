package nrg.inc.bykerz.profiles.interfaces.rest.transform;

import nrg.inc.bykerz.profiles.domain.model.commands.CreateProfileCommand;
import nrg.inc.bykerz.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(
                resource.userId(),
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.userId()
        );
    }
}
