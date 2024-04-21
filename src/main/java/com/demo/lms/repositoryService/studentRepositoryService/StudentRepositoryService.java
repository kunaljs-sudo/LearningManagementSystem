package com.demo.lms.repositoryService.studentRepositoryService;

import com.demo.lms.entity.Student;
import java.util.List;

public interface StudentRepositoryService {
    public List<Student> getAllStudent();

    public Student getStudentById(Integer id);

    public Student createStudent(Student student);

    public Student updateStudent(Student student);
}
