package com.npro.BudgetManagementService.Repositories;

import com.npro.BudgetManagementService.Model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long> {


    Optional<Income> findByGuid(String incomeGuid);
}
