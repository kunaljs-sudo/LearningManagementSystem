package com.demo.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.lms.dto.StudentDTO;
import com.demo.lms.exception.BadDataProvidedException;
import com.demo.lms.exchanges.StudentPostRequest;
import com.demo.lms.service.studentService.StudentService;;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/allStudents")
    public List<StudentDTO> getAllStudent() {
        return studentService.getAllStudent();
    }

    @GetMapping("/student_id")
    public StudentDTO getStudentByID(@PathVariable Integer student_id) {
        if (student_id == null) {
            throw new BadDataProvidedException("Bad data provided please check again");
        }
        return studentService.getStudentByID(student_id);
    }

    @PostMapping
    public StudentDTO registerNewSudent(@RequestBody StudentPostRequest studentPostRequest) {
        if (studentPostRequest == null || studentPostRequest.getName() == null
                || studentPostRequest.getName().isEmpty()) {
            throw new BadDataProvidedException("Bad data provided please check again");
        }
        return studentService.createStudent(studentPostRequest);
    }
}
