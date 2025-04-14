package com.npro.TaskManagementService.Exceptions;

import java.io.Serial;

public class NotFound extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NotFound(String message){
        super(message);
    }
}
