package com.smartjob.cl.bci.common.exception;

public class EmailException extends RuntimeException{

    public static final String INVALID_EMAIL = "The email has an invalid form:";
    public static final String EXISTS_EMAIL = "The email is already registered";

    public EmailException(String message) {
        super(message);
    }
}
