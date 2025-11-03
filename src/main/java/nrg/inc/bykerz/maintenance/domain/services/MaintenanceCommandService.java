package nrg.inc.bykerz.maintenance.domain.services;

import nrg.inc.bykerz.maintenance.domain.model.agreggates.Maintenance;
import nrg.inc.bykerz.maintenance.domain.model.commands.AssignExpenseToMaintenanceCommand;
import nrg.inc.bykerz.maintenance.domain.model.commands.CreateMaintenanceCommand;
import nrg.inc.bykerz.maintenance.domain.model.commands.DeleteMaintenanceCommand;
import nrg.inc.bykerz.maintenance.domain.model.commands.UpdateStateOfMaintenanceByIdCommand;

import java.util.Optional;

public interface MaintenanceCommandService {
    Optional<Maintenance> handle(CreateMaintenanceCommand command);
    void handle(DeleteMaintenanceCommand command);
    Optional<Maintenance> handle(AssignExpenseToMaintenanceCommand command);
    Optional<Maintenance> handle(UpdateStateOfMaintenanceByIdCommand command);
}
