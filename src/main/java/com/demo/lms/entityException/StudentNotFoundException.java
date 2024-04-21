package com.demo.lms.entityException;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super();
    }

    public StudentNotFoundException(String msg) {
        super(msg);
    }
}
