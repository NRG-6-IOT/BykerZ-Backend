package nrg.inc.bykerz.maintenance.domain.model.commands;

public record CreateExpenseCommand(
        String name,
        Double finalPrice,
        Long userId,
        String expenseType
) {
}
