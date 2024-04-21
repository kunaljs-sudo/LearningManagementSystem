package com.demo.lms.dto;

import java.util.List;

public class SubjectDTO {
    private Integer subject_id;
    private String name;
    private List<String> students;

    public SubjectDTO() {
    }

    public SubjectDTO(Integer subject_id, String name, List<String> students) {
        this.subject_id = subject_id;
        this.name = name;
        this.students = students;
    }

    public Integer getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }

}
