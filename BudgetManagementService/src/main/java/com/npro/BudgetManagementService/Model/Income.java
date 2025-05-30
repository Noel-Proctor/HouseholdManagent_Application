package com.npro.BudgetManagementService.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @Size(min = 1, max = 200, message = "Description must be between 1-200 characters long.")
    private String description;

    @DecimalMin("0.01")
    private Double amount;

    @NotNull
    private IncomeType incomeType;

    @NotNull
    private String guid;

    @NotNull
    private LocalDate incomeDate;

    @FutureOrPresent
    private LocalDate createdOn;

    @FutureOrPresent
    private LocalDate updatedOn;

    @NotNull
    @Size(min = 1, max = 50, message = "Expense Name must be between 1-50 characters long.")
    private String name;





}
