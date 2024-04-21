package com.demo.lms.exchanges;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExamStudentRegisterRequest {
    @JsonProperty("student_id")
    private Integer student_id;

    public ExamStudentRegisterRequest() {
    }

    @JsonCreator
    public ExamStudentRegisterRequest(@JsonProperty("student_id") Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getStudentId() {
        return student_id;
    }

    public void setStudentId(Integer student_id) {
        this.student_id = student_id;
    }

}
