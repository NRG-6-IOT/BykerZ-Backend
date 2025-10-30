package nrg.inc.bykerz.maintenance.domain.application.internal.commandservices;

import nrg.inc.bykerz.maintenance.domain.model.commands.SeedExpenseTypesCommand;
import nrg.inc.bykerz.maintenance.domain.model.entities.ExpenseType;
import nrg.inc.bykerz.maintenance.domain.model.valueobjects.ExpenseTypes;
import nrg.inc.bykerz.maintenance.domain.services.ExpenseTypeCommandService;
import nrg.inc.bykerz.maintenance.infrastructure.persistence.jpa.repositories.ExpenseTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ExpenseTypeCommandServiceImpl implements ExpenseTypeCommandService {

    private final ExpenseTypeRepository expenseTypeRepository;

    public ExpenseTypeCommandServiceImpl(ExpenseTypeRepository expenseTypeRepository) {
        this.expenseTypeRepository = expenseTypeRepository;
    }


    @Override
    public void handle(SeedExpenseTypesCommand command) {
        Arrays.stream(ExpenseTypes.values()).forEach(expenseType -> {
            if (!expenseTypeRepository.existsByName(expenseType)) {
                expenseTypeRepository.save(new ExpenseType(ExpenseTypes.valueOf(expenseType.name())));
            }
        });

    }
}
