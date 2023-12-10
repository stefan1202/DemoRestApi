package com.example.demorestapi.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenericException extends RuntimeException{
    public GenericException(String message) {
        super(message);
        log.error("GenericException Error happened: " + message);
    }
}
