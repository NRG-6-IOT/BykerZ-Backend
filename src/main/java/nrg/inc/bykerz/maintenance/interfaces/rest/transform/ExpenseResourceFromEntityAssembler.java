package nrg.inc.bykerz.maintenance.interfaces.rest.transform;

import nrg.inc.bykerz.maintenance.domain.model.agreggates.Expense;
import nrg.inc.bykerz.maintenance.interfaces.rest.resources.ExpenseResource;

import java.util.stream.Collectors;


public class ExpenseResourceFromEntityAssembler {
    public static ExpenseResource toResourceFromEntity(Expense entity
    ){

        return new ExpenseResource(
                entity.getId(),
                entity.getName(),
                entity.getFinalPrice(),
                entity.getExpenseType().getName().name(),
                entity.getExpenseItems().stream()
                        .map(ExpenseItemResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList())
        );
    }
}
