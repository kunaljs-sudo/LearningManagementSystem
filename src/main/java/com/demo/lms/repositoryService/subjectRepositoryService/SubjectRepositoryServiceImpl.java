package com.demo.lms.repositoryService.subjectRepositoryService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.lms.entity.Subject;
import com.demo.lms.exception.BadDataProvidedException;
import com.demo.lms.repository.SubjectRepository;

@Service
public class SubjectRepositoryServiceImpl implements SubjectRepositoryService {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectRepositoryServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(Integer id) {
        Optional<Subject> oSubject = subjectRepository.findById(id);
        if (oSubject == null || oSubject.isEmpty()) {
            throw new BadDataProvidedException("Wrong student_id provided " + id);
        }
        return oSubject.get();
    }

    @Override
    public Subject createSubject(Subject subject) {
        if (subject == null) {
            throw new BadDataProvidedException("Cannot have empty data");
        }
        return subjectRepository.save(subject);
    }

    @Override
    public Subject updateSubject(Subject subject) {
        if (subject == null || subject.getSubject_id() == null) {
            throw new BadDataProvidedException("Cannot have empty data");
        }
        return subjectRepository.save(subject);
    }

}
