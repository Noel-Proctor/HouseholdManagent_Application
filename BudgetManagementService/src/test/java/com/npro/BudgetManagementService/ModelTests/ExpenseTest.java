package com.npro.BudgetManagementService.ModelTests;
import com.npro.BudgetManagementService.Model.Expense;
import com.npro.BudgetManagementService.Model.ExpenseType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validExpense_ShouldPassValidation() {
        Expense expense = new Expense();
        expense.setName("Groceries");
        expense.setDescription("Weekly supermarket shopping");
        expense.setExpenseType(ExpenseType.GROCERIES);
        expense.setExpenseDate(LocalDate.now());
        expense.setAmount(50.0);
        expense.setCreatedOn(LocalDate.now());
        expense.setUpdatedOn(LocalDate.now());
        expense.setGuid(UUID.randomUUID().toString());

        Set<ConstraintViolation<Expense>> violations = validator.validate(expense);
        assertTrue(violations.isEmpty(), "There should be no validation errors");
    }

    @Test
    void blankName_ShouldFailValidation() {
        Expense expense = buildValidExpense();
        expense.setName("");

        Set<ConstraintViolation<Expense>> violations = validator.validate(expense);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    void nullAmount_ShouldFailValidation() {
        Expense expense = buildValidExpense();
        expense.setAmount(null);

        Set<ConstraintViolation<Expense>> violations = validator.validate(expense);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("amount")));
    }

    @Test
    void amountLessThanMin_ShouldFailValidation() {
        Expense expense = buildValidExpense();
        expense.setAmount(0.00);

        Set<ConstraintViolation<Expense>> violations = validator.validate(expense);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("amount")));
    }

    @Test
    void futureExpenseDate_ShouldPass() {
        Expense expense = buildValidExpense();
        expense.setExpenseDate(LocalDate.now().plusDays(1)); // valid

        Set<ConstraintViolation<Expense>> violations = validator.validate(expense);
        assertTrue(violations.isEmpty());
    }

    @Test
    void pastCreatedOn_ShouldFailValidation() {
        Expense expense = buildValidExpense();
        expense.setCreatedOn(LocalDate.now().minusDays(1)); // invalid

        Set<ConstraintViolation<Expense>> violations = validator.validate(expense);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("createdOn")));
    }

    // Utility method to build a valid expense for testing
    private Expense buildValidExpense() {
        Expense e = new Expense();
        e.setName("Valid Name");
        e.setDescription("Valid Description");
        e.setExpenseType(ExpenseType.HOUSEHOLD_COSTS);
        e.setExpenseDate(LocalDate.now());
        e.setAmount(25.00);
        e.setCreatedOn(LocalDate.now());
        e.setUpdatedOn(LocalDate.now());
        e.setGuid(UUID.randomUUID().toString());
        return e;
    }
}
