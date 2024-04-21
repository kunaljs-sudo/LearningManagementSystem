package com.demo.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.lms.entity.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {

}
