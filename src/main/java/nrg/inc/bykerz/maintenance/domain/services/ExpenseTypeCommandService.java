package nrg.inc.bykerz.maintenance.domain.services;

import nrg.inc.bykerz.maintenance.domain.model.commands.SeedExpenseTypesCommand;

public interface ExpenseTypeCommandService {
    void handle(SeedExpenseTypesCommand command);
}
