package com.npro.BudgetManagementService.Controllers;

import com.npro.BudgetManagementService.Config.AppConstants;
import com.npro.BudgetManagementService.Payload.APIResponse;
import com.npro.BudgetManagementService.Payload.BudgetDTO;
import com.npro.BudgetManagementService.Payload.BudgetPage;
import com.npro.BudgetManagementService.Payload.BudgetResponse;
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
}
