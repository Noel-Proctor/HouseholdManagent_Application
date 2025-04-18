package com.npro.BudgetManagementService.Service;

import com.npro.BudgetManagementService.Exceptions.NotFoundException;
import com.npro.BudgetManagementService.Model.Budget;
import com.npro.BudgetManagementService.Model.Expense;
import com.npro.BudgetManagementService.Payload.*;
import com.npro.BudgetManagementService.Repositories.BudgetRepository;
import com.npro.BudgetManagementService.Repositories.ExpenseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final ModelMapper modelMapper;
    private final BudgetRepository budgetRepository;
    private final ExpenseRepository expenseRepository;

    public BudgetServiceImpl(ModelMapper modelMapper, BudgetRepository budgetRepository, ExpenseRepository expenseRepository) {
        this.modelMapper = modelMapper;
        this.budgetRepository = budgetRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public BudgetResponse createNewBudget(BudgetDTO budgetDTO) {

        Budget budget = modelMapper.map(budgetDTO, Budget.class);
        budget.setCreatedOn(LocalDate.now());
        budget.setGuid(UUID.randomUUID().toString());


        BudgetResponse response = new BudgetResponse("Budget successfully created",
                modelMapper.map(budget, BudgetDTO.class));
        return response;
    }

    @Override
    public APIResponse deleteBudget(String guid) {
        try {
            Optional<Budget> budget = budgetRepository.findByGuid(guid);
            if (budget.isEmpty()) {
                throw new NotFoundException("Budget not found");
            }


            budgetRepository.deleteByGuid(guid);
        } catch (IllegalArgumentException e) {
            return new APIResponse("There was an error. Please contact your administrator", false);

        }
            return new APIResponse("Budget successfully deleted", true);

    }

    @Override
    public BudgetResponse getBudgetByGuid(String guid) {
        Optional<Budget> budget = budgetRepository.findByGuid(guid);
        if (budget.isEmpty()) {
            throw new NotFoundException("Budget not found");
        }

        BudgetResponse response = new BudgetResponse("Budget Found", modelMapper.map(budget.get(), BudgetDTO.class));
        return response;
    }



    @Override
    public BudgetPage getBudgetPage(Integer pageNumber, Integer pageSize, String sortBy, String direction) {

        Sort sortAndOrderBy = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()  : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortAndOrderBy);
        Page<Budget> page = budgetRepository.findAll(pageable);

        List<BudgetResponse> budgets = page.getContent().stream().map(budget ->
                new BudgetResponse("Budget Found", modelMapper.map(budget, BudgetDTO.class))).toList();

        BudgetPage budgetPage = new BudgetPage();
        budgetPage.setBudgetList(budgets);
        budgetPage.setCurrentPage(page.getNumber());
        budgetPage.setTotalPages(page.getTotalPages());
        budgetPage.setFirstPage(page.isFirst());
        budgetPage.setLastPage(page.isLast());
        budgetPage.setTotalElements(page.getNumberOfElements());
        return budgetPage;
    }

    @Override
    public APIResponse addExpenseToBudget(String guid, ExpenseDTO expense_In) {

        Budget budget = budgetRepository.findByGuid(guid).orElseThrow(
                ()-> new NotFoundException("Budget not found"));

        Expense expense = modelMapper.map(expense_In, Expense.class);
        expense.setCreatedOn(LocalDate.now());
        expense.setGuid(UUID.randomUUID().toString());

        budget.addExpense(expense);
        budget.addToTotalCost(expense.getAmount());
        budgetRepository.save(budget);

        return new APIResponse("Expense Successfully Added", true);

    }


    @Override
    public APIResponse removeExpenseFromBudget(String budgetGuid, String expenseGuid) {

        Budget budget = budgetRepository.findByGuid(budgetGuid).orElseThrow(
                ()-> new NotFoundException("Budget Not Found")
        );

        Expense expense = expenseRepository.findByGuid(expenseGuid).orElseThrow(
                ()-> new NotFoundException("Expense not found")
        );

        if(!budget.getExpenses().contains(expense)) {
            throw new NotFoundException("Expense does not belong to this budget");
        }

        budget.removeExpense(expense);
        budgetRepository.save(budget);

        return new APIResponse("Expense Successfully Removed", true);

    }


    @Override
    public APIResponse updateExpenseOnBudget(String budgetGuid, ExpenseDTO expenseDTO) {

        Budget budget = budgetRepository.findByGuid(budgetGuid).orElseThrow(
                ()-> new NotFoundException("Budget Not Found")
        );

        Expense expense = expenseRepository.findByGuid(expenseDTO.getGuid()).orElseThrow(
                ()-> new NotFoundException("Expense not found")
        );

        double originalExpenseAmount = expense.getAmount();
        expense.setUpdatedOn(LocalDate.now());
        expense.setExpenseType(expenseDTO.getExpenseType());

        double delta = expenseDTO.getAmount() - originalExpenseAmount;
        expense.setAmount(expenseDTO.getAmount());
        expense.setDescription(expenseDTO.getDescription());
        expense.setName(expenseDTO.getName());

        expenseRepository.save(expense);

        budget.addToTotalCost(delta);
        budgetRepository.save(budget);

        return new APIResponse("Expense Successfully Updated", true);

    }
}
