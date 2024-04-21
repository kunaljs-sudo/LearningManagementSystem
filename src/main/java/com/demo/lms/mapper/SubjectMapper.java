package com.demo.lms.mapper;

import java.util.stream.Collectors;

import com.demo.lms.dto.SubjectDTO;
import com.demo.lms.entity.Subject;
import com.demo.lms.exception.BadDataProvidedException;

public class SubjectMapper {
    public static SubjectDTO mapSubject2DTO(Subject subject) {
        if (subject == null) {
            throw new BadDataProvidedException("Provided Student Object is null");
        }
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setSubject_id(subject.getSubject_id());
        subjectDTO.setName(subject.getName());
        subjectDTO.setStudents(subject.getStudents()
                .stream()
                .map(student -> student.getName())
                .collect(Collectors.toList()));
        return subjectDTO;
    }
}
