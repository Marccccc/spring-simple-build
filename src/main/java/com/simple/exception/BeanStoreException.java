package com.simple.exception;

public class BeanStoreException extends RuntimeException {

    public BeanStoreException(String message) {
        super("XML loading " + message + " throw exception");
    }

}
