package org.kodo.enterpriseformbuilder.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FormNotFoundException.class)
    public String handleFormNotFoundException(FormNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(FormFieldNotFoundException.class)
    public String handleFormFieldNotFoundException(FormFieldNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidArgsException.class)
    public String handleInvalidArgsException(InvalidArgsException e) {
        return e.getMessage();
    }
}
