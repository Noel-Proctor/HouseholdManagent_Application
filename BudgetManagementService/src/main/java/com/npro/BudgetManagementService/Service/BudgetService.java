package com.npro.BudgetManagementService.Service;
import com.npro.BudgetManagementService.Payload.*;

public interface BudgetService {
    BudgetResponse createNewBudget(BudgetDTO budgetDTO);

    APIResponse deleteBudget(String guid);

    BudgetResponse getBudgetByGuid(String guid);

    BudgetPage getBudgetPage(Integer pageNumber, Integer pageSize, String sortBy, String direction);

    APIResponse addExpenseToBudget(String budgetGuid, ExpenseDTO expense);

    APIResponse removeExpenseFromBudget(String budgetGuid, String expenseGuid);

    APIResponse updateExpenseOnBudget(String budgetGuid, ExpenseDTO expense);


}