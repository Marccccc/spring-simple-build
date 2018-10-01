package com.simple.exception;

public class BeanCreationException extends BeanException {

    public BeanCreationException(String message) {
        super("XML loading " + message + " throw exception");
    }

}
