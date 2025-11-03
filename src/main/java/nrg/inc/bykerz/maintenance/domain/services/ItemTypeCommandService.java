package nrg.inc.bykerz.maintenance.domain.services;

import nrg.inc.bykerz.maintenance.domain.model.commands.SeedItemTypesCommand;

public interface ItemTypeCommandService {
    void handle(SeedItemTypesCommand command);
}
