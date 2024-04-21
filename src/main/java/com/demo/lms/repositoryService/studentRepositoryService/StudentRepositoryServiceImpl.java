package com.demo.lms.repositoryService.studentRepositoryService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.lms.entity.Student;
import com.demo.lms.exception.BadDataProvidedException;
import com.demo.lms.repository.StudentRepository;

@Service
public class StudentRepositoryServiceImpl implements StudentRepositoryService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentRepositoryServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Integer id) {
        Optional<Student> oStudent = studentRepository.findById(id);
        if (oStudent == null || oStudent.isEmpty()) {
            throw new BadDataProvidedException("Wrong student_id provided " + id);
        }
        return oStudent.get();
    }

    @Override
    public Student createStudent(Student student) {
        if (student == null) {
            throw new BadDataProvidedException("Cannot have empty data");
        }
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        if (student == null || student.getStudent_id() == null) {
            throw new BadDataProvidedException("Cannot have empty data");
        }
        return studentRepository.save(student);
    }

}
