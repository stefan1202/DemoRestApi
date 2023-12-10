package com.example.demorestapi.controller;

import com.example.demorestapi.exceptions.GenericException;
import com.example.demorestapi.exceptions.SdaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GeneralExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(GenericException.class)
    public String handleException(GenericException exception){
        return "There was a generic exception:" + exception.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException exception){
       log.error("There was an exception", exception);
        return "There was a runtime exception:" + exception.getMessage();
    }
}
