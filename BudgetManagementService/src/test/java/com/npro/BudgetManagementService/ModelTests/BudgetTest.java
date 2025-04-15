package com.npro.BudgetManagementService.ModelTests;

import com.npro.BudgetManagementService.Model.Budget;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class BudgetTest {

    private Budget validBudget;
    private Budget invalidBudget;

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

        validBudget = new Budget();
        invalidBudget = new Budget();
    }


    @Test
    void testValidBudget(){

        validBudget.setName("Valid Name");
        validBudget.setDescription("Valid Description");
        validBudget.setCreatedOn(LocalDate.now());
        validBudget.setUpdatedOn(LocalDate.now().plusDays(2));

        assertThat(validBudget.getName()).isEqualTo("Valid Name");
        assertThat(validBudget.getDescription()).isEqualTo("Valid Description");
        assertEquals("Description should match", "Valid Description", validBudget.getDescription());
        assertThat(validBudget.getCreatedOn()).isEqualTo(LocalDate.now());
        assertEquals("Updated on should match", LocalDate.now().plusDays(2), validBudget.getUpdatedOn());


    }

    @Test
    void testInvalidBudget(){
        invalidBudget.setName("This task name is longer than 100 characters. This task name is longer than 100 characters. Lets make is longer.");
        Set<ConstraintViolation<Budget>> violations  = validator.validate(invalidBudget);

        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch( violation -> violation.getMessage().contains("Budget name must be between 1-50 characters long.") );

        invalidBudget.setName("");
        violations  = validator.validate(invalidBudget);
        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch( violation -> violation.getMessage().contains("Budget name must be between 1-50 characters long.") );

        invalidBudget.setName("   ");
        violations  = validator.validate(invalidBudget);
        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch( violation -> violation.getMessage().contains("Budget name must be between 1-50 characters long.") );
    }

}
