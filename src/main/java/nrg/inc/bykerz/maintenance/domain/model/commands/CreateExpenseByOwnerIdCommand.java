package nrg.inc.bykerz.maintenance.domain.model.commands;

public record CreateExpenseByOwnerIdCommand(
        String name,
        Double finalPrice,
        Long ownerId,
        String expenseType
) {
}
