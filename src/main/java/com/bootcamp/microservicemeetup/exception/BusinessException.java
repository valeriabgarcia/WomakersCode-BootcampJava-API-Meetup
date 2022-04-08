package com.bootcamp.microservicemeetup.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String e) {
        super(e);
    }
}
