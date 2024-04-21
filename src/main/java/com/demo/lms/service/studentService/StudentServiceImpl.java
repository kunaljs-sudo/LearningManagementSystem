package com.demo.lms.service.studentService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.lms.dto.StudentDTO;
import com.demo.lms.entity.Student;
import com.demo.lms.exception.BadDataProvidedException;
import com.demo.lms.exchanges.StudentPostRequest;
import com.demo.lms.mapper.StudentMapper;
import com.demo.lms.repositoryService.studentRepositoryService.StudentRepositoryService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepositoryService studentRepositoryService;

    @Override
    public List<StudentDTO> getAllStudent() {
        List<Student> students = studentRepositoryService.getAllStudent();
        return students.stream().map(student -> StudentMapper.mapStudent2StudentDTO(student))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentByID(Integer student_id) {
        Student student = studentRepositoryService.getStudentById(student_id);
        return StudentMapper.mapStudent2StudentDTO(student);
    }

    @Override
    public StudentDTO createStudent(StudentPostRequest studentPostRequest) {
        if (studentPostRequest == null || studentPostRequest.getName() == null
                || studentPostRequest.getName().isBlank()) {
            throw new BadDataProvidedException("Bad data provided please check again");
        }
        Student student = new Student();
        student.setName(studentPostRequest.getName());
        student.setExams(new ArrayList<>());
        student.setSubjects(new ArrayList<>());
        // update student variable to created one
        student = studentRepositoryService.createStudent(student);
        return StudentMapper.mapStudent2StudentDTO(student);
    }

}
