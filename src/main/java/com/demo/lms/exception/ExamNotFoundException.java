package com.demo.lms.exception;

public class ExamNotFoundException extends RuntimeException {
    public ExamNotFoundException() {
        super();
    }

    public ExamNotFoundException(String msg) {
        super(msg);
    }

}
