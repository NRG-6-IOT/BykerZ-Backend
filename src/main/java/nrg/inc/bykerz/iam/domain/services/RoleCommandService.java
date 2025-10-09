package nrg.inc.bykerz.iam.domain.services;

import nrg.inc.bykerz.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {

    void handle(SeedRolesCommand seedRolesCommand);

}
