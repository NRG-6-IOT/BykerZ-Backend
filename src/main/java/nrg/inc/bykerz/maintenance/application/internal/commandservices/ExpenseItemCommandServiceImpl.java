package nrg.inc.bykerz.maintenance.application.internal.commandservices;

import nrg.inc.bykerz.maintenance.domain.model.agreggates.ExpenseItem;
import nrg.inc.bykerz.maintenance.domain.model.commands.AddExpenseItemCommand;
import nrg.inc.bykerz.maintenance.domain.model.commands.DeleteExpenseItemsByExpenseIdCommand;
import nrg.inc.bykerz.maintenance.domain.model.entities.ItemType;
import nrg.inc.bykerz.maintenance.domain.model.valueobjects.ItemTypes;
import nrg.inc.bykerz.maintenance.domain.services.ExpenseItemCommandService;
import nrg.inc.bykerz.maintenance.infrastructure.persistence.jpa.repositories.ExpenseItemRepository;
import nrg.inc.bykerz.maintenance.infrastructure.persistence.jpa.repositories.ExpenseRepository;
import org.springframework.stereotype.Service;
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

        var expense = expenseRepository.findById(command.expenseId());

        var expenseItem = new ExpenseItem(
                command.name(),
                command.amount(),
                command.unitPrice(),
                command.totalPrice(),
                expense.get(),
                new ItemType(ItemTypes.valueOf(command.itemType()))
        );

        try {
            expenseItemRepository.save(expenseItem);
            return Optional.of(expenseItem);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while creating expense item: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteExpenseItemsByExpenseIdCommand command) {
        expenseItemRepository.deleteExpenseItemsByExpense_Id(command.expenseId());
    }
}
