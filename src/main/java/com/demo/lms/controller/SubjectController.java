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

import com.demo.lms.dto.SubjectDTO;
import com.demo.lms.exception.BadDataProvidedException;
import com.demo.lms.exchanges.StudentSubjectEnrollRequest;
import com.demo.lms.exchanges.SubjectPostRequest;
import com.demo.lms.service.subjectService.SubjectService;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public List<SubjectDTO> getAllSubject() {
        return subjectService.getAllSubject();
    }

    @GetMapping("/{subject_id}")
    public SubjectDTO getSubjectById(@PathVariable Integer subject_id) {
        if (subject_id == null) {
            throw new BadDataProvidedException("Provided data is null");
        }
        return subjectService.getSubjectById(subject_id);
    }

    @PostMapping
    public SubjectDTO createSubject(@RequestBody SubjectPostRequest subjectPostRequest) {
        if (subjectPostRequest == null || subjectPostRequest.getName() == null
                || subjectPostRequest.getName().isBlank()) {
            throw new BadDataProvidedException("Data provided is null");
        }

        return subjectService.createSubject(subjectPostRequest);
    }

    @PutMapping("/{subject_id}")
    public SubjectDTO enrollStudentInSubject(@PathVariable Integer subject_id,
            @RequestBody StudentSubjectEnrollRequest studentSubjectEnrollRequest) {
        if (subject_id == null || studentSubjectEnrollRequest == null
                || studentSubjectEnrollRequest.getStudent_id() == null) {
            throw new BadDataProvidedException("Data provided is null");
        }

        return subjectService.enrollStudentInSubject(subject_id, studentSubjectEnrollRequest);
    }
}
