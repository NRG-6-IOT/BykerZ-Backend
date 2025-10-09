package nrg.inc.bykerz.iam.rest.transform;

import nrg.inc.bykerz.iam.domain.model.commands.CreateUserCommand;
import nrg.inc.bykerz.iam.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateUserResource createUserResource) {
        return new CreateUserCommand(
                createUserResource.username(),
                createUserResource.password(),
                createUserResource.roles().stream().toList());
    }
}
