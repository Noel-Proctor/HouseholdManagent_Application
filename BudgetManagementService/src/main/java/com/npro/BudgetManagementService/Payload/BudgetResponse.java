package com.npro.BudgetManagementService.Payload;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class BudgetResponse {
    private String message;
    private BudgetDTO budget;

    public BudgetResponse(String message, BudgetDTO budget) {
        this.message = message;
        this.budget = budget;
    }
}
