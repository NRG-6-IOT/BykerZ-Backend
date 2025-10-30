package nrg.inc.bykerz.maintenance.domain.services;

import nrg.inc.bykerz.maintenance.domain.model.agreggates.ExpenseItem;
import nrg.inc.bykerz.maintenance.domain.model.commands.AddExpenseItemCommand;
import nrg.inc.bykerz.maintenance.domain.model.commands.DeleteExpenseItemsByExpenseIdCommand;

import java.util.List;

public interface ExpenseItemCommandService {
    List<ExpenseItem> handle(AddExpenseItemCommand command);
    void handle(DeleteExpenseItemsByExpenseIdCommand command);
}
