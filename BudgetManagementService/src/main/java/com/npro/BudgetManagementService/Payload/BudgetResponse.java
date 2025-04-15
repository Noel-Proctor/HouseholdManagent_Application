package com.npro.BudgetManagementService.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetResponse {


    private BudgetDTO budget;

    private List<IncomeResponse> incomeList;

    private List<ExpenseResponse> expenseList;
}
