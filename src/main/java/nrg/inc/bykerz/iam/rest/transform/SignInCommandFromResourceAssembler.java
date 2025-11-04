package nrg.inc.bykerz.iam.rest.transform;

import nrg.inc.bykerz.iam.domain.model.commands.SignInCommand;
import nrg.inc.bykerz.iam.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource){
        return new SignInCommand(
                signInResource.username(),
                signInResource.password()
        );
    }
}
