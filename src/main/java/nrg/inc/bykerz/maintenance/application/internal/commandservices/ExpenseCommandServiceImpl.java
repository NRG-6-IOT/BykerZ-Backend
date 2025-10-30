package nrg.inc.bykerz.maintenance.application.internal.commandservices;

import nrg.inc.bykerz.maintenance.domain.model.agreggates.Expense;
import nrg.inc.bykerz.maintenance.domain.model.commands.CreateExpenseCommand;
import nrg.inc.bykerz.maintenance.domain.model.commands.DeleteExpenseCommand;
import nrg.inc.bykerz.maintenance.domain.services.ExpenseCommandService;
import nrg.inc.bykerz.maintenance.infrastructure.persistence.jpa.repositories.ExpenseRepository;

import java.util.Optional;

public class ExpenseCommandServiceImpl implements ExpenseCommandService {

    private final ExpenseRepository expenseRepository;

    public ExpenseCommandServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Optional<Expense> handle(CreateExpenseCommand command) {
        return Optional.empty();
    }

    @Override
    public void handle(DeleteExpenseCommand command) {
        var expense = expenseRepository.findById(command.expenseId());

        if (expense.isEmpty()) {
            throw new IllegalArgumentException("Expense with id " + command.expenseId() + " not found");
        }

        try {
            expenseRepository.delete(expense.get());
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting expense: " + e.getMessage());
        }
    }
}
