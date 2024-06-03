package com.nmb.sportwear_store.exception;

import com.nmb.sportwear_store.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(FailedUserUpdateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleFailedUserUpdateException(final FailedUserUpdateException e) {
        return new ErrorMessage(e, e.getMessage());
    }
}
