package com.telle.budget.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class NotFoundException extends BudgetRuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
