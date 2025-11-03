package nrg.inc.bykerz.iam.interfaces.rest.transform;

import nrg.inc.bykerz.iam.domain.model.commands.SignUpCommand;
import nrg.inc.bykerz.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource signUpResource) {
        return new SignUpCommand(
                signUpResource.username(),
                signUpResource.password(),
                signUpResource.roles()
        );
    }
}
