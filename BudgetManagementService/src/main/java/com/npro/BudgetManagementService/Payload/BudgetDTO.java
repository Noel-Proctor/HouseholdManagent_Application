package com.npro.BudgetManagementService.Payload;

import com.npro.BudgetManagementService.Model.Expense;
import com.npro.BudgetManagementService.Model.Income;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetDTO {

    private String name;
    private UUID guid;
    private String description;
    private List<Income> incomes;
    private List<Expense> expenses;



}
