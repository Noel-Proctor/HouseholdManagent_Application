package com.npro.BudgetManagementService.Payload;
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
    private List<IncomeDTO> incomes;
    private List<ExpenseDTO> expenses;
    private double balance;
    private double totalCosts;
    private double totalIncome;



}
