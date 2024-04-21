package com.demo.lms.dto;

import java.util.List;

public class ExamDTO {

    private Integer exam_id;
    private String subject;
    List<String> students;

    public ExamDTO() {
    }

    public ExamDTO(Integer exam_id, String subject, List<String> students) {
        this.exam_id = exam_id;
        this.subject = subject;
        this.students = students;
    }

    public Integer getExam_id() {
        return exam_id;
    }

    public void setExam_id(Integer exam_id) {
        this.exam_id = exam_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "ExamDTO [exam_id=" + exam_id + ", subject=" + subject + ", students=" + students.toString()
                + "]";
    }

}
