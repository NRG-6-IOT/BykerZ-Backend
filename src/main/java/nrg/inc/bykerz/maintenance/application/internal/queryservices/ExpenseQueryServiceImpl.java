package nrg.inc.bykerz.maintenance.application.internal.queryservices;

import nrg.inc.bykerz.maintenance.domain.model.agreggates.Expense;
import nrg.inc.bykerz.maintenance.domain.model.queries.GetAllExpensesByUserIdQuery;
import nrg.inc.bykerz.maintenance.domain.model.queries.GetExpenseByIdQuery;
import nrg.inc.bykerz.maintenance.domain.services.ExpenseQueryService;
import nrg.inc.bykerz.maintenance.infrastructure.persistence.jpa.repositories.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseQueryServiceImpl implements ExpenseQueryService {

    private final ExpenseRepository expenseRepository;

    public ExpenseQueryServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Transactional(readOnly = true)
    public List<Expense> handle(GetAllExpensesByUserIdQuery query) {
        return expenseRepository.getExpensesByUser_Id(query.userId());
    }

    @Transactional(readOnly = true)
    public Optional<Expense> handle(GetExpenseByIdQuery query) {
        return expenseRepository.findById(query.expenseId());
    }
}
