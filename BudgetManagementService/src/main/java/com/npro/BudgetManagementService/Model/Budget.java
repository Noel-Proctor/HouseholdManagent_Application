package com.npro.BudgetManagementService.Model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "budget", orphanRemoval = true)
    private List<Expense> expenses;

    @DecimalMin("0.00")
    private double totalCosts= 0.00;

    @DecimalMin("0.00")
    private double totalIncome = 0.00;

    @Setter(AccessLevel.PRIVATE)
    private double balance =0.00;

    @FutureOrPresent
    private LocalDate createdOn;

    @FutureOrPresent
    private LocalDate updatedOn;

    public void addToTotalCost(double amount){
        setTotalCosts(getTotalCosts() + amount);
        setBalance();

    }

    public void addToTotalIncome(double amount){
        setTotalIncome(getTotalIncome() + amount);
        setBalance();
    }

    private void setBalance(){
        this.balance = getTotalIncome() - getTotalCosts();
    }

    public void addExpense(Expense expense){
        expenses.add(expense);
        expense.setBudget(this);
    }

    public void removeExpense(Expense expense){
        setTotalCosts(getTotalCosts() - expense.getAmount());
        setBalance();
        expenses.remove(expense);
        expense.setBudget(null);
    }

    public void addIncome(Income income){
        setTotalIncome(getTotalIncome() + income.getAmount());
        setBalance();
        incomes.add(income);
        income.setBudget(this);
    }

    public void removeIncome(Income income){
        setTotalIncome(getTotalIncome() - income.getAmount());
        setBalance();
        incomes.remove(income);
        income.setBudget(null);
    }

}
