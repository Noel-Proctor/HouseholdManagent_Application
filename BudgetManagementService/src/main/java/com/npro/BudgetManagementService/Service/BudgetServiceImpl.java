package com.npro.BudgetManagementService.Service;

import com.npro.BudgetManagementService.Exceptions.NotFoundException;
import com.npro.BudgetManagementService.Model.Budget;
import com.npro.BudgetManagementService.Payload.APIResponse;
import com.npro.BudgetManagementService.Payload.BudgetDTO;
import com.npro.BudgetManagementService.Payload.BudgetPage;
import com.npro.BudgetManagementService.Payload.BudgetResponse;
import com.npro.BudgetManagementService.Repositories.BudgetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;
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

    public BudgetServiceImpl(ModelMapper modelMapper, BudgetRepository budgetRepository) {
        this.modelMapper = modelMapper;
        this.budgetRepository = budgetRepository;
    }

    @Override
    public BudgetResponse createNewBudget(BudgetDTO budgetDTO) {

        Budget budget = modelMapper.map(budgetDTO, Budget.class);
        budget.setCreatedOn(LocalDate.now());
        budget.setGuid(UUID.randomUUID().toString());
        Budget newBudget = budgetRepository.save(budget);

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
}
