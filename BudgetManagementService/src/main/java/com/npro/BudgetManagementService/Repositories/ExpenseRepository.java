package com.npro.BudgetManagementService.Repositories;

import com.npro.BudgetManagementService.Model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense> findByGuid(String guid);

}
