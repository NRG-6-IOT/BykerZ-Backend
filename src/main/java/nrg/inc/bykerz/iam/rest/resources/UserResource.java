package nrg.inc.bykerz.iam.rest.resources;

import nrg.inc.bykerz.iam.domain.model.valueobjects.Roles;

import java.util.List;

public record UserResource(
        Long id,
        String username,
        List<Roles> roles
) { }
