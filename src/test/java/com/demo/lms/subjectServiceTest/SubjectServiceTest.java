package com.demo.lms.subjectServiceTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

import com.demo.lms.LmsApplication;
import com.demo.lms.entity.Student;
import com.demo.lms.entity.Subject;
import com.demo.lms.exception.UnableToRegisterStudent;
import com.demo.lms.exchanges.StudentSubjectEnrollRequest;
import com.demo.lms.repositoryService.studentRepositoryService.StudentRepositoryService;
import com.demo.lms.repositoryService.subjectRepositoryService.SubjectRepositoryService;
import com.demo.lms.service.subjectService.SubjectServiceImpl;

@SpringBootTest(classes = { LmsApplication.class })
@DirtiesContext
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@ActiveProfiles("test")
public class SubjectServiceTest {

    @MockBean
    private SubjectRepositoryService subjectRepositoryService;

    @MockBean
    private StudentRepositoryService studentRepositoryService;

    @InjectMocks
    private SubjectServiceImpl subjectService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        Subject subject1 = new Subject(1, "maths", new ArrayList<>());
        Subject subject2 = new Subject(2, "physics", new ArrayList<>());
        Subject subject3 = new Subject(3, "chemistry", new ArrayList<>());
        Subject subject4 = new Subject(4, "english", new ArrayList<>());
        subjects.add(subject1);
        subjects.add(subject2);
        subjects.add(subject3);
        subjects.add(subject4);
        return subjects;
    }

    private Student getStudent(Integer student_id) {
        Student student = new Student();
        student.setStudent_id(student_id);
        student.setName("kunal");
        student.setExams(new ArrayList<>());
        student.setSubjects(getAllSubjects());
        return student;

    }

    @Test
    public void enrollStudentInSubjectTestThrowsException() {
        Student student = getStudent(1);
        Subject subject = new Subject(1, "maths", new ArrayList<>());

        when(subjectRepositoryService.getSubjectById(1)).thenReturn(subject);
        when(studentRepositoryService.getStudentById(1)).thenReturn(student);

        StudentSubjectEnrollRequest studentSubjectEnrollRequest = new StudentSubjectEnrollRequest(1);

        assertThrows(UnableToRegisterStudent.class,
                () -> subjectService.enrollStudentInSubject(1, studentSubjectEnrollRequest));

    }

}
