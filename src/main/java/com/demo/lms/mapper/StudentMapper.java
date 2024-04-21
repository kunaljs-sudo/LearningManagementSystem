package com.demo.lms.mapper;

import java.util.stream.Collectors;

import com.demo.lms.dto.StudentDTO;
import com.demo.lms.entity.Student;
import com.demo.lms.exception.BadDataProvidedException;

public class StudentMapper {

    public static StudentDTO mapStudent2StudentDTO(Student student) {
        if (student == null) {
            throw new BadDataProvidedException("Provided Student Object is null");
        }
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudent_id(student.getStudent_id());
        studentDTO.setName(student.getName());
        studentDTO.setSubjects(student.getSubjects()
                .stream()
                .map(subject -> subject.getName())
                .collect(Collectors.toList()));
        studentDTO.setExams(student.getExams()
                .stream()
                .map(exam -> String.valueOf(exam.getExam_id()))
                .collect(Collectors.toList()));
        return studentDTO;
    }
}
