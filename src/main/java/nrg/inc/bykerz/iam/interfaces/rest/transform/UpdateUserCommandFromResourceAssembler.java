package nrg.inc.bykerz.iam.interfaces.rest.transform;

import nrg.inc.bykerz.iam.domain.model.commands.UpdateUserCommand;
import nrg.inc.bykerz.iam.interfaces.rest.resources.UpdateUserResource;

public class UpdateUserCommandFromResourceAssembler {
    public static UpdateUserCommand toCommandFromResource(UpdateUserResource updateUserResource){
        return new UpdateUserCommand(
                updateUserResource.username(),
                updateUserResource.password());
    }
}
