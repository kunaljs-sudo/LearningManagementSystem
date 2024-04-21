package com.demo.lms.dto;

import java.util.List;

public class StudentDTO {

    private Integer student_id;
    private String name;
    private List<String> subjects;
    private List<String> exams;

    public StudentDTO() {
    }

    public StudentDTO(Integer student_id, String name, List<String> subjects, List<String> exams) {
        this.student_id = student_id;
        this.name = name;
        this.subjects = subjects;
        this.exams = exams;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getExams() {
        return exams;
    }

    public void setExams(List<String> exams) {
        this.exams = exams;
    }
}
