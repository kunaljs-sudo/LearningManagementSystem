package com.demo.lms.repositoryService.examRepositoryService;

import com.demo.lms.entity.Exam;
import java.util.List;

public interface ExamRepositoryService {
    public List<Exam> getAllExams();

    public Exam getExamById(Integer id);

    public Exam createExam(Exam exam);

    public Exam updateExam(Exam exam);
}
