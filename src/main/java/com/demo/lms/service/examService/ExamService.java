package com.demo.lms.service.examService;

import java.util.List;

import com.demo.lms.dto.ExamDTO;
import com.demo.lms.exchanges.ExamPostRequest;
import com.demo.lms.exchanges.ExamStudentRegisterRequest;

public interface ExamService {
    public List<ExamDTO> getAllExam();

    public ExamDTO getExamByID(Integer exam_id);

    public ExamDTO createExam(ExamPostRequest examPostRequest);

    public ExamDTO registerStudent(Integer exam_id, ExamStudentRegisterRequest examStudentRegisterRequest);
}
