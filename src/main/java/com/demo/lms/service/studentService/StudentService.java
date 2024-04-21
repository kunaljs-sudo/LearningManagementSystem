package com.demo.lms.service.studentService;

import java.util.List;

import com.demo.lms.dto.StudentDTO;
import com.demo.lms.exchanges.StudentPostRequest;

public interface StudentService {
    public List<StudentDTO> getAllStudent();

    public StudentDTO getStudentByID(Integer student_id);

    public StudentDTO createStudent(StudentPostRequest studentPostRequest);
}
