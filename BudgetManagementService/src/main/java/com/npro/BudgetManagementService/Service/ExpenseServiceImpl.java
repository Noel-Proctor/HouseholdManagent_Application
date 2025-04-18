package com.npro.BudgetManagementService.Service;


import com.npro.BudgetManagementService.Exceptions.NotFoundException;
import com.npro.BudgetManagementService.Model.Budget;
import com.npro.BudgetManagementService.Model.Expense;
import com.npro.BudgetManagementService.Payload.ExpenseDTO;
import com.npro.BudgetManagementService.Payload.ExpenseResponse;
import com.npro.BudgetManagementService.Repositories.BudgetRepository;
import com.npro.BudgetManagementService.Repositories.ExpenseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ModelMapper modelMapper;
    private final ExpenseRepository expenseRepository;
    private final BudgetRepository budgetRepository;

    public ExpenseServiceImpl(ModelMapper modelMapper, ExpenseRepository expenseRepository, BudgetRepository budgetRepository) {
        this.modelMapper = modelMapper;
        this.expenseRepository = expenseRepository;
        this.budgetRepository = budgetRepository;
    }
//    Restructured. Spring should handle this when an expense is added to a budgets expense list.
//    @Override
//    public ExpenseResponse createExpense(ExpenseDTO expenseDTO, String budgetId) {
//
//        Expense expense = modelMapper.map(expenseDTO, Expense.class);
//        expense.setCreatedOn(LocalDate.now());
//        expense.setGuid(UUID.randomUUID().toString());
//
//        Budget budget = budgetRepository.findByGuid(budgetId).orElseThrow(() -> new NotFoundException("Budget Not Found"));
//        budget.addToTotalCost(expense.getAmount());
//        budgetRepository.save(budget);
//        expense.setBudget(budget);
//        Expense savedExpense = expenseRepository.save(expense);
//        ExpenseResponse expenseResponse = new ExpenseResponse();
//        expenseResponse.setMessage("Expense successfully created.");
//        expenseResponse.setSuccess(Boolean.TRUE);
//        expenseResponse.setExpenseDto(modelMapper.map(savedExpense, ExpenseDTO.class));
//
//        return expenseResponse;
//    }

    //    Restructured. Spring should handle this when an expense is updated on a budgets expense list.
//    @Override
//    public ExpenseResponse updateExpense(ExpenseDTO expenseDTO) {
//
//        Expense expense = expenseRepository.findByGuid(expenseDTO.getGuid()).orElseThrow(
//                () -> new NotFoundException("Expense Not Found")
//        );
//
//        expense.setUpdatedOn(LocalDate.now());
//        expense.setExpenseType(expenseDTO.getExpenseType());
//
//// We need to keep the original expense amount to update the total value of the budget
//        double originalExpenseAmount = expense.getAmount();
//        Budget budget = budgetRepository.findByGuid(expense.getBudget().getGuid()).orElseThrow(
//                () -> new NotFoundException("Budget Not Found")
//        );
//
//        double delta = expenseDTO.getAmount() - originalExpenseAmount;
//        expense.setAmount(expenseDTO.getAmount());
//        expense.setDescription(expenseDTO.getDescription());
//        expense.setName(expenseDTO.getName());
//        expense = expenseRepository.save(expense);
//        ExpenseDTO savedExpense = modelMapper.map(expense, ExpenseDTO.class);
//        ExpenseResponse expenseResponse = new ExpenseResponse("Expense saved successfully", true, savedExpense);
//
//        budget.addToTotalCost(delta);
//        budgetRepository.save(budget);
//        return expenseResponse;
//    }

    @Override
    public ExpenseResponse getExpense(String guid) {
        Expense expense = expenseRepository.findByGuid(guid).orElseThrow(() ->
                new NotFoundException("Expense Not Found"
                ));
        ExpenseResponse response = new ExpenseResponse("Expense Found", true, modelMapper.map(expense, ExpenseDTO.class));
        return response;
    }

    @Override
    public List<ExpenseResponse> getAllExpenses(String budget_Guid) {

        Budget budget = budgetRepository.findByGuid(budget_Guid).orElseThrow(
                ()-> new NotFoundException("Budget Not Found"));

        List<ExpenseResponse> response = budget.getExpenses().stream().map(
                expense -> new ExpenseResponse("Expense Found", true, modelMapper.map(expense, ExpenseDTO.class))
        ).toList();

        return response;
    }


}
