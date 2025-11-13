package nrg.inc.bykerz.iam.interfaces.rest.transform;

import nrg.inc.bykerz.iam.domain.model.commands.SignUpCommand;
import nrg.inc.bykerz.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource signUpResource) {
        return new SignUpCommand(
                signUpResource.firstName(),
                signUpResource.lastName(),
                signUpResource.username(),
                signUpResource.email(),
                signUpResource.photoUrl(),
                signUpResource.password(),
                signUpResource.roles()
        );
    }
}
