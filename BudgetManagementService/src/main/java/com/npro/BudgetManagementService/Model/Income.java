package com.npro.BudgetManagementService.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

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

    private String description;
    private Double amount;
    private IncomeType incomeType;
    private LocalDate incomeDate;
    private LocalDate createdOn;
    private LocalDate updatedOn;





}
