package nrg.inc.bykerz.iam.rest.transform;

import nrg.inc.bykerz.iam.domain.model.entities.Role;
import nrg.inc.bykerz.iam.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role roleEntity) {

        return new RoleResource(
                roleEntity.getId(),
                roleEntity.getStringName()
        );
    }
}
