package com.demo.lms.repositoryService.subjectRepositoryService;

import com.demo.lms.entity.Subject;
import java.util.List;

public interface SubjectRepositoryService {
    public List<Subject> getAllSubjects();

    public Subject getSubjectById(Integer id);

    public Subject createSubject(Subject subject);

    public Subject updateSubject(Subject subject);
}
