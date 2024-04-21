package com.demo.lms.service.subjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.lms.dto.SubjectDTO;
import com.demo.lms.entity.Student;
import com.demo.lms.entity.Subject;
import com.demo.lms.exception.BadDataProvidedException;
import com.demo.lms.exception.UnableToRegisterStudent;
import com.demo.lms.exchanges.StudentSubjectEnrollRequest;
import com.demo.lms.exchanges.SubjectPostRequest;
import com.demo.lms.mapper.SubjectMapper;
import com.demo.lms.repositoryService.studentRepositoryService.StudentRepositoryService;
import com.demo.lms.repositoryService.subjectRepositoryService.SubjectRepositoryService;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepositoryService subjectRepositoryService;

    @Autowired
    private StudentRepositoryService studentRepositoryService;

    @Override
    public List<SubjectDTO> getAllSubject() {
        List<Subject> subjects = subjectRepositoryService.getAllSubjects();
        return subjects.stream().map(subject -> SubjectMapper.mapSubject2DTO(subject)).collect(Collectors.toList());
    }

    @Override
    public SubjectDTO getSubjectById(Integer subject_id) {
        Subject subject = subjectRepositoryService.getSubjectById(subject_id);
        return SubjectMapper.mapSubject2DTO(subject);
    }

    @Override
    public SubjectDTO createSubject(SubjectPostRequest subjectPostRequest) {
        if (subjectPostRequest == null || subjectPostRequest.getName().isBlank()) {
            throw new BadDataProvidedException("Provided SubjectPostRequest object is null or name is null");
        }
        Subject subject = new Subject();
        subject.setName(subjectPostRequest.getName());
        subject.setStudents(new ArrayList<>());

        Subject createdSubject = subjectRepositoryService.createSubject(subject);
        return SubjectMapper.mapSubject2DTO(createdSubject);
    }

    @Override
    public SubjectDTO enrollStudentInSubject(Integer subject_id,
            StudentSubjectEnrollRequest studentSubjectEnrollRequest) {
        if (studentSubjectEnrollRequest == null || studentSubjectEnrollRequest.getStudent_id() == null) {
            throw new BadDataProvidedException("Data provided is null");
        }
        Subject subject = subjectRepositoryService.getSubjectById(subject_id);
        Student student = studentRepositoryService.getStudentById(studentSubjectEnrollRequest.getStudent_id());

        // if already enrolled in that subject
        Optional<Subject> studentAlreadyEnrolled = student.getSubjects().stream()
                .filter(sub -> sub.getSubject_id() == subject.getSubject_id()).findAny();

        // if student already enrolled in that subject we cann;t enroll him again
        if (studentAlreadyEnrolled.isPresent()) {
            throw new UnableToRegisterStudent(
                    "Student " + student.getName() + " has already enrolled in subject " + subject.getName());
        }

        subject.getStudents().add(student);
        subjectRepositoryService.updateSubject(subject);
        return SubjectMapper.mapSubject2DTO(subjectRepositoryService.getSubjectById(subject_id));
    }

}
