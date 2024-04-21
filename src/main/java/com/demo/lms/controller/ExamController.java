package com.demo.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.lms.dto.ExamDTO;
import com.demo.lms.exception.BadDataProvidedException;
import com.demo.lms.exchanges.ExamPostRequest;
import com.demo.lms.exchanges.ExamStudentRegisterRequest;
import com.demo.lms.service.examService.ExamService;

@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping
    public List<ExamDTO> getAllExam() {
        return examService.getAllExam();
    }

    @PutMapping("/{exam_id}")
    public ExamDTO registerStudentInExam(@PathVariable Integer exam_id,
            @RequestBody ExamStudentRegisterRequest examStudentRegisterRequest) {
        return examService.registerStudent(exam_id, examStudentRegisterRequest);
    }

    @PostMapping
    public ExamDTO createExam(@RequestBody ExamPostRequest examPostRequest) {
        if (examPostRequest == null || examPostRequest.getSubject_id() == null) {
            throw new BadDataProvidedException("Provided Data is null");
        }
        return examService.createExam(examPostRequest);
    }
}
