package nrg.inc.bykerz.maintenance.domain.services;

import nrg.inc.bykerz.maintenance.domain.model.agreggates.Expense;
import nrg.inc.bykerz.maintenance.domain.model.queries.GetAllExpensesByUserIdQuery;
import nrg.inc.bykerz.maintenance.domain.model.queries.GetExpenseByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ExpenseQueryService {
    List<Expense> handle(GetAllExpensesByUserIdQuery query);
    Optional<Expense> handle(GetExpenseByIdQuery query);
}
