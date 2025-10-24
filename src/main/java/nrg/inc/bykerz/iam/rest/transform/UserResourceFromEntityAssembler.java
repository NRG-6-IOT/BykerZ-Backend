package nrg.inc.bykerz.iam.rest.transform;

import nrg.inc.bykerz.iam.domain.model.aggregates.User;
import nrg.inc.bykerz.iam.domain.model.entities.Role;
import nrg.inc.bykerz.iam.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        return new UserResource(
                user.getId(),
                user.getUsername(),
                user.getUserRoles().stream().map(Role::getName).toList()
        );
    }

}
