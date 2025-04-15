package com.npro.BudgetManagementService.Payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponse {

    private String messsage;
    private List<ExpenseDTO> espenses;

}
