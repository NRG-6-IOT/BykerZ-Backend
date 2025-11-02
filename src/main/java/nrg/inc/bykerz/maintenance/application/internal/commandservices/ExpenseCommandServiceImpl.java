package nrg.inc.bykerz.maintenance.application.internal.commandservices;

import nrg.inc.bykerz.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import nrg.inc.bykerz.maintenance.domain.model.agreggates.Expense;
import nrg.inc.bykerz.maintenance.domain.model.commands.CreateExpenseCommand;
import nrg.inc.bykerz.maintenance.domain.model.commands.DeleteExpenseCommand;
import nrg.inc.bykerz.maintenance.domain.model.entities.ExpenseType;
import nrg.inc.bykerz.maintenance.domain.model.valueobjects.ExpenseTypes;
import nrg.inc.bykerz.maintenance.domain.services.ExpenseCommandService;
import nrg.inc.bykerz.maintenance.infrastructure.persistence.jpa.repositories.ExpenseItemRepository;
import nrg.inc.bykerz.maintenance.infrastructure.persistence.jpa.repositories.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ExpenseCommandServiceImpl implements ExpenseCommandService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseItemRepository expenseItemRepository;
    private final UserRepository userRepository;

    public ExpenseCommandServiceImpl(ExpenseRepository expenseRepository, ExpenseItemRepository expenseItemRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.expenseItemRepository = expenseItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Optional<Expense> handle(CreateExpenseCommand command) {

        var user = userRepository.findById(command.userId());

        var expense = new Expense(
                command.name(),
                command.finalPrice(),
                user.get(),
                new ExpenseType(ExpenseTypes.valueOf(command.expenseType()))
        );

        try {
            expenseRepository.save(expense);
            return Optional.of(expense);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while creating expense: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void handle(DeleteExpenseCommand command) {
        var expense = expenseRepository.findById(command.expenseId());

        if (expense.isEmpty()) {
            throw new IllegalArgumentException("Expense with id " + command.expenseId() + " not found");
        }

        try {
            expenseItemRepository.deleteExpenseItemsByExpense_Id(command.expenseId());
            expenseRepository.delete(expense.get());
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting expense: " + e.getMessage());
        }
    }
}
