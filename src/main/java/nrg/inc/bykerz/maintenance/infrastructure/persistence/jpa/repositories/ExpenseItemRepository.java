package nrg.inc.bykerz.maintenance.infrastructure.persistence.jpa.repositories;


import nrg.inc.bykerz.maintenance.domain.model.agreggates.ExpenseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseItemRepository extends JpaRepository<ExpenseItem,Long> {
    void deleteExpenseItemsByExpense_Id(Long expenseId);
}
