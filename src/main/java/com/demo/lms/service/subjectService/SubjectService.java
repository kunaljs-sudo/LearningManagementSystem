package com.demo.lms.service.subjectService;

import java.util.List;

import com.demo.lms.dto.SubjectDTO;
import com.demo.lms.exchanges.StudentSubjectEnrollRequest;
import com.demo.lms.exchanges.SubjectPostRequest;

public interface SubjectService {
    public List<SubjectDTO> getAllSubject();

    public SubjectDTO getSubjectById(Integer subject_id);

    public SubjectDTO createSubject(SubjectPostRequest subjectPostRequest);

    public SubjectDTO enrollStudentInSubject(Integer subject_id,
            StudentSubjectEnrollRequest studentSubjectEnrollRequest);
}
