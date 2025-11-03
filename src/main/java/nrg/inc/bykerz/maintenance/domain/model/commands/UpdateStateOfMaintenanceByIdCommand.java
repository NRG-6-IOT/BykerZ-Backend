package nrg.inc.bykerz.maintenance.domain.model.commands;

public record UpdateStateOfMaintenanceByIdCommand(String state, Long maintenanceId) {
}
