package com.npro.BudgetManagementService.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "budget")
    private List<Income> incomes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "budget")
    private List<Expense> expenses;

    private LocalDate createdOn;
    private LocalDate updatedOn;

}
