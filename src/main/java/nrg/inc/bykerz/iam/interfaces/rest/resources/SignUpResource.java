package nrg.inc.bykerz.iam.interfaces.rest.resources;

import nrg.inc.bykerz.iam.domain.model.valueobjects.Roles;

import java.util.List;

public record SignUpResource(
        String username,
        String password,
        List<Roles> roles
) {
}
