package org.zina.api.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(value= HttpStatus.NOT_FOUND) //404
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public void notFound() {}
}
