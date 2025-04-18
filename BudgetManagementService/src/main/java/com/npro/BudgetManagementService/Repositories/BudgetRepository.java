package com.npro.BudgetManagementService.Repositories;

import com.npro.BudgetManagementService.Model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    @Transactional
     void deleteByGuid(String guid);

    Optional<Budget> findByGuid(String guid);

}
