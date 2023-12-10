package com.example.demorestapi.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SdaException extends RuntimeException{
    public SdaException(String message) {
        super(message);
        log.error("Error happened: " + message);
    }
}
