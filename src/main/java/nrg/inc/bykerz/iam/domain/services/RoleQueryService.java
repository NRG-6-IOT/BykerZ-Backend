package nrg.inc.bykerz.iam.domain.services;

import nrg.inc.bykerz.iam.domain.model.entities.Role;
import nrg.inc.bykerz.iam.domain.model.queries.GetAllRolesQuery;

import java.util.List;

public interface RoleQueryService {

    List<Role> handle(GetAllRolesQuery getAllRolesQuery);
}
