package com.npro.TaskManagementService.model;
import com.npro.TaskManagementService.Model.Task;
import com.npro.TaskManagementService.Model.TaskStatus;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class TaskTest {

    private Validator validator;

    @BeforeEach
    void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidTaskCreate(){
        Task task = new Task();
        task.setGuid(UUID.randomUUID().toString());
        task.setStatus(TaskStatus.TO_DO);
        task.setCreatedDate(LocalDate.now());
        task.setDescription("Test Description");
        task.setDueDate(LocalDate.now().plusDays(1));
    }

    @Test
    void testInvalidTaskNameExceedsMaxLength(){
        Task task = new Task();
        task.setTaskName("This task name is longer than 100 characters. This task name is longer than 100 characters. Lets make is longer.");
        task.setGuid(UUID.randomUUID().toString());
        Set<ConstraintViolation<Task>> violations = validator.validate(task);

        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(
                violation -> violation.getPropertyPath().toString().equals("taskName")
                && violation.getMessage().equals("Task name cannot be greater than 100 characters."));
    }

    @Test
    void testInvalidTaskNameIsNull(){
        Task task = new Task();
        task.setGuid(UUID.randomUUID().toString());
        Set<ConstraintViolation<Task>> violations = validator.validate(task);

        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(violation->
                violation.getPropertyPath().toString().equals("taskName")
        && violation.getMessage().equals("Task name cannot be empty."));

    }

    @Test
    void testInvalidGuidIsEmpty(){
        Task task = new Task();
        task.setStatus(TaskStatus.TO_DO);
        task.setCreatedDate(LocalDate.now());
        task.setDescription("Test Description");
        task.setDueDate(LocalDate.now().plusDays(1));

        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(violation->
                violation.getPropertyPath().toString().equals("guid")
        && violation.getMessage().equals("must not be blank"));
    }


    @Test()
    void testCompletedDateInvalid(){
        Task task = new Task();
        task.setDueDate(LocalDate.now());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            task.setCompletedDate(LocalDate.now().minusDays(1));
        });
        assertEquals("Testing completed date", "Completed date cannot be before due date", thrown.getMessage());

    }


}
