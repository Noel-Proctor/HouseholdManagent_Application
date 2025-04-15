package com.npro.BudgetManagementService.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;

    private String description;
    private ExpenseType expenseType;
    private LocalDate expenseDate;
    private Double amount;
    private LocalDate createdOn;
    private LocalDate updatedOn;


}
