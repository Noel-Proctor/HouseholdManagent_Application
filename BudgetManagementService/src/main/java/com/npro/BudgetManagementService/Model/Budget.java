package com.npro.BudgetManagementService.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank
    private String guid;

    public void setName(
            @Size(min = 1, max = 50, message = "Budget name must be between 1-50 characters long.")
            String name) {
        this.name = name.trim();
    }

    @Size(min = 1, max = 50, message = "Budget name must be between 1-50 characters long.")
    private String name;

    @Size(min = 1, max = 200, message = "Description must be between 1-200 characters long.")
    private String description;
    public void setDescription(String description) {
        this.description = description.trim();
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "budget")
    private List<Income> incomes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "budget")
    private List<Expense> expenses;

    @FutureOrPresent
    private LocalDate createdOn;

    @FutureOrPresent
    private LocalDate updatedOn;

}
