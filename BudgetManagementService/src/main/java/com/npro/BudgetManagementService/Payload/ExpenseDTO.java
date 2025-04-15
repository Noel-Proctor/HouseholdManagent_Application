package com.npro.BudgetManagementService.Payload;


import com.npro.BudgetManagementService.Model.Budget;
import com.npro.BudgetManagementService.Model.ExpenseType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {


    private String description;
    private ExpenseType expenseType;
    private Double amount;
    private LocalDate expenseDate;

}
