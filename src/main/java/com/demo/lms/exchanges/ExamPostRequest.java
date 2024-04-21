package com.demo.lms.exchanges;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExamPostRequest {
    @JsonProperty("subject_id")
    private Integer subject_id;

    @JsonCreator
    public ExamPostRequest(@JsonProperty("subject_id") Integer subject_id) {
        this.subject_id = subject_id;
    }

    public Integer getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
    }

}
