package nrg.inc.bykerz.maintenance.domain.services;

import nrg.inc.bykerz.maintenance.domain.model.commands.SeedMaintenanceStatesCommand;

public interface MaintenanceStateCommandService {
    void handle(SeedMaintenanceStatesCommand command);
}
