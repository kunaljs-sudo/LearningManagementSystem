package com.demo.lms.entityException;

public class ExamNotFoundException extends RuntimeException {
    public ExamNotFoundException() {
        super();
    }

    public ExamNotFoundException(String msg) {
        super(msg);
    }
}
