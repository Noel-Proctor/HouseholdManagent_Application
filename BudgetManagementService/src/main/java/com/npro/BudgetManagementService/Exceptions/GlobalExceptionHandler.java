package com.npro.BudgetManagementService.Exceptions;

import com.npro.BudgetManagementService.Payload.APIResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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


    @ExceptionHandler(TransactionSystemException.class)
    public APIResponse transactionSystemExceptionHandler(TransactionSystemException e){

        if(e.getRootCause() instanceof ConstraintViolationException){
            APIResponse response = constraintViolationExceptionHandler((ConstraintViolationException) e.getRootCause());
            return response;
        }

        return new APIResponse("There was an error. Please contact your administrator", false);
    }

    @ExceptionHandler(NotFoundException.class)
    public APIResponse notFoundExceptionHandler(NotFoundException e){
        return new APIResponse(e.getMessage(), false);
    }
}
