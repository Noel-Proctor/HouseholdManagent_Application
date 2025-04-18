package com.npro.BudgetManagementService.Service;
import com.npro.BudgetManagementService.Payload.ExpenseResponse;

import java.util.List;

public interface ExpenseService {

//    ExpenseResponse createExpense(ExpenseDTO expenseDTO, String budgetGuid);

//    ExpenseResponse updateExpense(ExpenseDTO expenseDTO);

    ExpenseResponse getExpense(String guid);

    List<ExpenseResponse> getAllExpenses(String guid);

//    APIResponse deleteExpense(String guid);
}
