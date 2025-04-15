package com.npro.BudgetManagementService.Payload;

import com.npro.BudgetManagementService.Model.Budget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetPage {

    private List<BudgetResponse> budgetList;
    private boolean firstPage;
    private Integer totalPages;
    private Integer currentPage;
    private Integer totalElements;
    private Integer size;
    private Integer total;
    private boolean lastPage;
}
