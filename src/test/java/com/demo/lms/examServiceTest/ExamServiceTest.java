package com.demo.lms.examServiceTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.demo.lms.entity.Exam;
import com.demo.lms.entity.Student;
import com.demo.lms.entity.Subject;
import com.demo.lms.exception.UnableToRegisterStudent;
import com.demo.lms.exchanges.ExamStudentRegisterRequest;
import com.demo.lms.repositoryService.examRepositoryService.ExamRepositoryService;
import com.demo.lms.repositoryService.studentRepositoryService.StudentRepositoryService;
import com.demo.lms.repositoryService.subjectRepositoryService.SubjectRepositoryService;
import com.demo.lms.service.examService.ExamServiceImpl;

@SpringBootTest
@DirtiesContext
@ActiveProfiles("test")
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class ExamServiceTest {
    @MockBean
    private ExamRepositoryService examRepositoryService;

    @MockBean
    private SubjectRepositoryService subjectRepositoryService;

    @MockBean
    private StudentRepositoryService studentRepositoryService;

    @InjectMocks
    private ExamServiceImpl examService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        // Subject subject1 = new Subject(1, "maths", new ArrayList<>());
        Subject subject2 = new Subject(2, "physics", new ArrayList<>());
        Subject subject3 = new Subject(3, "chemistry", new ArrayList<>());
        Subject subject4 = new Subject(4, "english", new ArrayList<>());
        // subjects.add(subject1);
        subjects.add(subject2);
        subjects.add(subject3);
        subjects.add(subject4);
        return subjects;
    }

    private Student getStudent(Integer student_id, Integer exam_id) {
        Student student = new Student();
        student.setStudent_id(student_id);
        student.setName("kunal");
        List<Exam> exams = new ArrayList<>();
        exams.add(getExam(exam_id));
        student.setExams(exams);
        student.setSubjects(getAllSubjects());
        return student;

    }

    private Subject getSubject(Integer subject_id) {
        return new Subject(1, "maths", new ArrayList<>());
    }

    private Exam getExam(Integer examId) {
        Exam exam = new Exam(examId, getSubject(examId), new ArrayList<>());
        return exam;

    }

    @Test
    public void regsterStudentTestthrowsException() {
        when(studentRepositoryService.getStudentById(1)).thenReturn(getStudent(1, 1));
        when(examRepositoryService.getExamById(1)).thenReturn(getExam(1));
        // student has not been enrolled into subject_id = 1
        ExamStudentRegisterRequest examStudentRegisterRequest = new ExamStudentRegisterRequest(1);
        assertThrows(UnableToRegisterStudent.class, () -> examService.registerStudent(1, examStudentRegisterRequest));
    }

}
