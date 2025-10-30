package nrg.inc.bykerz.maintenance.domain.services;

import nrg.inc.bykerz.maintenance.domain.model.agreggates.Expense;
import nrg.inc.bykerz.maintenance.domain.model.queries.GetAllExpensesByUserIdQuery;

import java.util.List;

public interface ExpenseQueryService {
    List<Expense> handle(GetAllExpensesByUserIdQuery query);
}
