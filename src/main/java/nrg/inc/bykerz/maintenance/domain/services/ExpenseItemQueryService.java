package nrg.inc.bykerz.maintenance.domain.services;

import nrg.inc.bykerz.maintenance.domain.model.agreggates.ExpenseItem;
import nrg.inc.bykerz.maintenance.domain.model.queries.GetAllExpenseItemsByExpenseIdQuery;

import java.util.List;

public interface ExpenseItemQueryService {
    List<ExpenseItem> handle(GetAllExpenseItemsByExpenseIdQuery query);
}
