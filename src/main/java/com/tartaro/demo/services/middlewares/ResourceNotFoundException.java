package com.tartaro.demo.services.middlewares;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Object obj) {
        super("Resource not found: " + obj);
    }

    private static final long serialVersionUID = 1L;
}
