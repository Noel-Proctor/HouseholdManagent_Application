package com.npro.BudgetManagementService.Payload;

import com.npro.BudgetManagementService.Model.Expense;
import com.npro.BudgetManagementService.Model.Income;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetDTO {

    private String name;
    private String description;
    private List<Income> incomes;
    private List<Expense> expenses;



}
