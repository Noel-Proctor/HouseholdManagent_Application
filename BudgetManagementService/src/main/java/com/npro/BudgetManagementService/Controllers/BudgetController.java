package com.npro.BudgetManagementService.Controllers;

import com.npro.BudgetManagementService.Config.AppConstants;
import com.npro.BudgetManagementService.Payload.*;
import com.npro.BudgetManagementService.Service.BudgetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/budget/")
@RestController
public class BudgetController {

    private final BudgetService budgetService;


    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping("create")
   public ResponseEntity<BudgetResponse> createBudget(@RequestBody BudgetDTO budgetDTO){
       BudgetResponse response = budgetService.createNewBudget(budgetDTO);
       return new ResponseEntity<>(response, HttpStatus.CREATED);
   }

     @DeleteMapping("delete/{guid}")
    public ResponseEntity<APIResponse> deleteBudget(@PathVariable String guid){
       APIResponse response = budgetService.deleteBudget(guid);
       return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @GetMapping("view/{guid}")
   public ResponseEntity<BudgetResponse> getBudget(@PathVariable String guid){
        BudgetResponse response = budgetService.getBudgetByGuid(guid);

        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @GetMapping("viewall")
   public ResponseEntity<BudgetPage> getBudgetPage(
           @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
           @RequestParam(name ="pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
           @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
           @RequestParam(name = "direction", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION) String direction
   ){
        BudgetPage response = budgetService.getBudgetPage(pageNumber, pageSize, sortBy, direction);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
   }

   @PostMapping("expense/add/{guid}")
    public ResponseEntity<APIResponse> addExpense(
            @PathVariable String guid, @RequestBody ExpenseDTO expenseDTO){
        APIResponse response = budgetService.addExpenseToBudget(guid, expenseDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
   }


   @DeleteMapping("expense/remove/{budgetGuid}/{expenseGuid}")
    public ResponseEntity<APIResponse> removeExpense(@PathVariable String budgetGuid, @PathVariable String expenseGuid){

        APIResponse response = budgetService.removeExpenseFromBudget(budgetGuid, expenseGuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
   }


   @PutMapping("expense/update/{budgetGuid}")
    public ResponseEntity<APIResponse> updateExpense(@PathVariable String budgetGuid, @RequestBody ExpenseDTO expenseDTO){
        APIResponse response = budgetService.updateExpenseOnBudget(budgetGuid, expenseDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("income/add/{guid}")
    public ResponseEntity<APIResponse> addIncome(@PathVariable String guid, @RequestBody IncomeDTO incomeDTO){
        APIResponse response = budgetService.addIncomeToBudget(guid, incomeDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
