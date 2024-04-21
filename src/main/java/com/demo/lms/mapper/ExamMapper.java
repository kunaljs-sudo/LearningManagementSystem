package com.demo.lms.mapper;

import java.util.stream.Collectors;

import com.demo.lms.dto.ExamDTO;
import com.demo.lms.entity.Exam;
import com.demo.lms.exception.BadDataProvidedException;

public class ExamMapper {

    public static ExamDTO mapExamToExamDTO(Exam exam) {
        if (exam == null || exam.getExam_id() == null) {
            throw new BadDataProvidedException("Provided exam ojbect or exam_id is null");
        }
        ExamDTO examDTO = new ExamDTO();
        examDTO.setExam_id(exam.getExam_id());
        examDTO.setSubject(exam.getSubject().getName());
        examDTO.setStudents(exam.getStudents()
                .stream()
                .map(student -> (student.getName() + " - Exams Enrolled: [" +
                        (student.getExams()
                                .stream()
                                .map(ex -> String.valueOf(ex.getExam_id()))
                                .collect(Collectors.joining(",")))
                        + "]"))
                .collect(Collectors.toList()));
        return examDTO;
    }
}
