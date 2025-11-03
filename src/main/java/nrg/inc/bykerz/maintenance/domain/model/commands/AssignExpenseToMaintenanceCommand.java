package nrg.inc.bykerz.maintenance.domain.model.commands;

public record AssignExpenseToMaintenanceCommand(Long maintenanceId, Long expenseId) {
}
