package com.demo.lms.service.examService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.lms.dto.ExamDTO;
import com.demo.lms.entity.Exam;
import com.demo.lms.entity.Student;
import com.demo.lms.entity.Subject;
import com.demo.lms.exception.BadDataProvidedException;
import com.demo.lms.exception.UnableToRegisterStudent;
import com.demo.lms.exchanges.ExamPostRequest;
import com.demo.lms.exchanges.ExamStudentRegisterRequest;
import com.demo.lms.mapper.ExamMapper;
import com.demo.lms.repositoryService.examRepositoryService.ExamRepositoryService;
import com.demo.lms.repositoryService.studentRepositoryService.StudentRepositoryService;
import com.demo.lms.repositoryService.subjectRepositoryService.SubjectRepositoryService;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepositoryService examRepositoryService;

    @Autowired
    private SubjectRepositoryService subjectRepositoryService;

    @Autowired
    private StudentRepositoryService studentRepositoryService;

    @Override
    public List<ExamDTO> getAllExam() {
        List<Exam> exams = examRepositoryService.getAllExams();
        return exams.stream().map(exam -> ExamMapper.mapExamToExamDTO(exam)).collect(Collectors.toList());

    }

    @Override
    public ExamDTO getExamByID(Integer exam_id) {
        Exam exam = examRepositoryService.getExamById(exam_id);
        return ExamMapper.mapExamToExamDTO(exam);
    }

    @Override
    public ExamDTO createExam(ExamPostRequest examPostRequest) {
        if (examPostRequest == null || examPostRequest.getSubject_id() == null) {
            throw new BadDataProvidedException("Provided examPostRequest object is null");
        }
        Subject subject = subjectRepositoryService.getSubjectById(examPostRequest.getSubject_id());
        Exam exam = new Exam();
        exam.setSubject(subject);
        exam.setStudents(new ArrayList<>());

        Exam createdExam = examRepositoryService.createExam(exam);
        return ExamMapper.mapExamToExamDTO(createdExam);

    }

    @Override
    public ExamDTO registerStudent(Integer exam_id, ExamStudentRegisterRequest examStudentRegisterRequest) {
        if (examStudentRegisterRequest == null || exam_id == null) {
            throw new BadDataProvidedException("Provided examStudentRegisterRequest object or exam_id is null");
        }
        // get student to whom we want to register
        Student student = studentRepositoryService.getStudentById(examStudentRegisterRequest.getStudentId());
        // get exam in which we want student to be registered
        final Exam exam = examRepositoryService.getExamById(exam_id);

        Optional<Subject> subjectFound = student.getSubjects()
                .stream()
                .filter(subj -> subj.getSubject_id() == exam.getSubject().getSubject_id())
                .findAny();

        // if student is not enrolled in the subject we want to register him for that
        // exam
        if (subjectFound.isEmpty()) {
            throw new UnableToRegisterStudent(
                    "Student" + student.getName()
                            + " you want to register for exam has not been enrolled in the subject: "
                            + exam.getSubject().getName() + " Provided exam_id: "
                            + exam_id + " and Student_id: " + examStudentRegisterRequest.getStudentId());
        }

        // if student is already enrolled in that particular exam
        Optional<Exam> alreadyEnrolledInExam = student.getExams().stream()
                .filter(ex -> ex.getExam_id() == exam.getExam_id()).findAny();

        if (alreadyEnrolledInExam.isPresent()) {
            throw new UnableToRegisterStudent(
                    "Student " + student.getName() + " has already been enrolled in exam: " + exam.getExam_id());
        }

        // if no error has been thrown meaning Student is enrolled
        // in the subject of which we want to enroll him/her in exam
        // so we can register this student
        student.getExams().add(exam);
        exam.getStudents().add(student);

        // studentRepositoryService.updateStudent(student);
        examRepositoryService.updateExam(exam);
        Exam updated_exam = examRepositoryService.getExamById(exam_id);

        return ExamMapper.mapExamToExamDTO(updated_exam);

    }

}
