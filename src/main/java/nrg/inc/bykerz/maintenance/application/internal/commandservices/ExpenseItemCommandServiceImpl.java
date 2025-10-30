package nrg.inc.bykerz.maintenance.application.internal.commandservices;

import nrg.inc.bykerz.maintenance.domain.model.agreggates.ExpenseItem;
import nrg.inc.bykerz.maintenance.domain.model.commands.AddExpenseItemCommand;
import nrg.inc.bykerz.maintenance.domain.model.commands.DeleteExpenseItemsByExpenseIdCommand;
import nrg.inc.bykerz.maintenance.domain.services.ExpenseItemCommandService;
import nrg.inc.bykerz.maintenance.infrastructure.persistence.jpa.repositories.ExpenseItemRepository;
import nrg.inc.bykerz.maintenance.infrastructure.persistence.jpa.repositories.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseItemCommandServiceImpl implements ExpenseItemCommandService {

    private final ExpenseItemRepository expenseItemRepository;
    private final ExpenseRepository expenseRepository;

    public ExpenseItemCommandServiceImpl(ExpenseItemRepository expenseItemRepository, ExpenseRepository expenseRepository) {
        this.expenseItemRepository = expenseItemRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Optional<ExpenseItem> handle(AddExpenseItemCommand command) {
        return Optional.empty();
    }

    @Override
    public void handle(DeleteExpenseItemsByExpenseIdCommand command) {
        expenseItemRepository.deleteExpenseItemsByExpense_Id(command.expenseId());
    }
}
