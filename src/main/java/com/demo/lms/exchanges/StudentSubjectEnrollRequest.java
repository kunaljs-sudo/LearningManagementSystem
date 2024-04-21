package com.demo.lms.exchanges;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentSubjectEnrollRequest {
    @JsonProperty("student_id")
    private Integer student_id;

    @JsonCreator
    public StudentSubjectEnrollRequest(@JsonProperty("student_id") Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

}
