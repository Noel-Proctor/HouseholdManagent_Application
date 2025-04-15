package com.npro.BudgetManagementService.Service;


import com.npro.BudgetManagementService.Payload.APIResponse;
import com.npro.BudgetManagementService.Payload.BudgetDTO;
import com.npro.BudgetManagementService.Payload.BudgetPage;
import com.npro.BudgetManagementService.Payload.BudgetResponse;

public interface BudgetService {
    BudgetResponse createNewBudget(BudgetDTO budgetDTO);

    APIResponse deleteBudget(String guid);

    BudgetResponse getBudgetByGuid(String guid);

    BudgetPage getBudgetPage(Integer pageNumber, Integer pageSize, String sortBy, String direction);
}
