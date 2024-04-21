package com.demo.lms.exception;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException() {
        super();
    }

    public SubjectNotFoundException(String msg) {
        super(msg);
    }
}
