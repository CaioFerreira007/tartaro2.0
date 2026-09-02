package com.tartaro.demo.services.middlewares;

public class DataBaseException extends RuntimeException {
    public DataBaseException(String message) {
        super(message);
    }

    private static final long serialVersionUID = 1L;
}
