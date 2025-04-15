package com.npro.BudgetManagementService.Exceptions;

import com.npro.BudgetManagementService.Payload.APIResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public APIResponse generalExceptionHandler(Exception e){

//    Add logging and built a ApplicationMonitoring service application that allows a user
//        to view logged messages.
        return new APIResponse(
                "There was an error. If the error persists please contact your administrator",
                false);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public APIResponse constraintViolationExceptionHandler(ConstraintViolationException e){

        StringBuilder builder = new StringBuilder();
        e.getConstraintViolations().forEach(constraint ->
                builder.append(
                      constraint.getPropertyPath().toString()).append(
                              ": ").append(constraint.getMessage()).append(System.lineSeparator())
                );



        return new APIResponse(builder.toString(), false);
    }

    @ExceptionHandler(NotFoundException.class)
    public APIResponse notFoundExceptionHandler(NotFoundException e){
        return new APIResponse(e.getMessage(), false);
    }
}
