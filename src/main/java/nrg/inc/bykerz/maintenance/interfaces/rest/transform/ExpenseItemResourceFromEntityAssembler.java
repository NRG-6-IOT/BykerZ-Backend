package nrg.inc.bykerz.maintenance.interfaces.rest.transform;

import nrg.inc.bykerz.maintenance.domain.model.agreggates.ExpenseItem;
import nrg.inc.bykerz.maintenance.interfaces.rest.resources.ExpenseItemResource;

public class ExpenseItemResourceFromEntityAssembler {
    public static ExpenseItemResource toResourceFromEntity(ExpenseItem entity){
        return new ExpenseItemResource(
                entity.getId(),
                entity.getName(),
                entity.getAmount(),
                entity.getUnitPrice(),
                entity.getTotalPrice(),
                entity.getItemType().getName().name()
        );
    }
}
