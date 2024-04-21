package com.demo.lms.repository;

import org.springframework.stereotype.Repository;

import com.demo.lms.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
