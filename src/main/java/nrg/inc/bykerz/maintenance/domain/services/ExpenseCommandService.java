package nrg.inc.bykerz.maintenance.domain.services;

import nrg.inc.bykerz.maintenance.domain.model.agreggates.Expense;
import nrg.inc.bykerz.maintenance.domain.model.commands.CreateExpenseCommand;
import nrg.inc.bykerz.maintenance.domain.model.commands.DeleteExpenseCommand;

import java.util.Optional;

public interface ExpenseCommandService {
    Optional<Expense> handle(CreateExpenseCommand command);
    void handle(DeleteExpenseCommand command);
}
