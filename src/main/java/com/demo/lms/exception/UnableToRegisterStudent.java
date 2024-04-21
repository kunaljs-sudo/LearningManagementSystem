package com.demo.lms.exception;

public class UnableToRegisterStudent extends RuntimeException {
    public UnableToRegisterStudent() {
        super();
    }

    public UnableToRegisterStudent(String msg) {
        super(msg);
    }
}
