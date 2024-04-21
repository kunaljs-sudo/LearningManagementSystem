package com.demo.lms.entityException;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException() {
        super();
    }

    public SubjectNotFoundException(String msg) {
        super(msg);
    }
}
