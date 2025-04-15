package com.npro.BudgetManagementService.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @Size(min = 1, max = 50, message = "Expense Name must be between 1-50 characters long.")
    private String name;

    @Size(min = 1, max = 200, message = "Description must be between 1-200 characters long.")
    private String description;

    @NotNull
    private ExpenseType expenseType;

    @NotNull
    private LocalDate expenseDate;

    @NotNull
    @DecimalMin("0.01")
    private Double amount;

    @NotNull
    @FutureOrPresent
    private LocalDate createdOn;

    @FutureOrPresent
    private LocalDate updatedOn;


}
