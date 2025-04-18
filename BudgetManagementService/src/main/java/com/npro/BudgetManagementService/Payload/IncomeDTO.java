package com.npro.BudgetManagementService.Payload;


import com.npro.BudgetManagementService.Model.IncomeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeDTO {
    private String description;
    private Double amount;
    private IncomeType incomeType;
    private LocalDate incomeDate;
    private String guid;
    private String name;

}
