package com.demo.lms.repositoryService.examRepositoryService;

import com.demo.lms.entity.Exam;
import com.demo.lms.exception.BadDataProvidedException;
import com.demo.lms.repository.ExamRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamRepositoryServiceImpl implements ExamRepositoryService {
    private final ExamRepository examRepository;

    @Autowired
    public ExamRepositoryServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public Exam getExamById(Integer id) {
        Optional<Exam> oExam = examRepository.findById(id);
        if (oExam == null || oExam.isEmpty()) {
            throw new BadDataProvidedException("Wrong student_id provided " + id);
        }
        return oExam.get();
    }

    @Override
    public Exam createExam(Exam exam) {
        if (exam == null) {
            throw new BadDataProvidedException("Cannot have empty data");
        }
        return examRepository.save(exam);
    }

    @Override
    public Exam updateExam(Exam exam) {
        if (exam == null || exam.getExam_id() == null) {
            throw new BadDataProvidedException("Cannot have empty data");
        }
        return examRepository.save(exam);
    }
}
