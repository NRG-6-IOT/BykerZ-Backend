package nrg.inc.bykerz.iam.interfaces.rest.transform;

import nrg.inc.bykerz.iam.domain.model.aggregates.User;
import nrg.inc.bykerz.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(
                user.getId(),
                user.getUsername(),
                token,
                user.getUserRoles().stream().map(
                        role -> role.getName().name()
                ).toList()
        );
    }
}
