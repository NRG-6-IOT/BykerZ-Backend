package nrg.inc.bykerz.maintenance.domain.model.commands;

public record AddExpenseItemCommand(
        Long expenseId,
        String name,
        Integer amount,
        Double unitPrice,
        Double totalPrice,
        String itemType
) {
}
