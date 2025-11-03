package nrg.inc.bykerz.maintenance.interfaces.rest.resources;

public record CreateExpenseItemResource(
        String name,
        Integer amount,
        Double unitPrice,
        Double totalPrice,
        String itemType
) {
}
