package com.npro.BudgetManagementService.Controllers;
import com.npro.BudgetManagementService.Payload.ExpenseResponse;
import com.npro.BudgetManagementService.Service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/expense/")
@RestController
public class ExpenseController {


    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

//
//    @PostMapping("create/{budgetGuid}")
//    public ResponseEntity<ExpenseResponse> createExpense(@RequestBody ExpenseDTO expenseDTO, @PathVariable String budgetGuid){
//        ExpenseResponse response = expenseService.createExpense(expenseDTO, budgetGuid);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

//    @PutMapping("update")
//    public ResponseEntity<ExpenseResponse> updateExpense(@RequestBody ExpenseDTO expenseDTO){
//        ExpenseResponse response = expenseService.updateExpense(expenseDTO);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @GetMapping("view/{guid}")
    public ResponseEntity<ExpenseResponse> getExpense(@PathVariable String guid){
        ExpenseResponse response = expenseService.getExpense(guid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("viewAll/{guid}")
    public ResponseEntity<List<ExpenseResponse>> getAllExpenses(@PathVariable String guid){
        List<ExpenseResponse> response = expenseService.getAllExpenses(guid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @DeleteMapping("delete/{guid}")
//    public ResponseEntity<APIResponse> deleteExpense(@PathVariable String guid){
//        APIResponse response = expenseService.deleteExpense(guid);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }



}
