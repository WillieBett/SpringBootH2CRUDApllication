package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Student;

public interface IStudentRepository extends JpaRepository<Student, Integer>{
    List<Student> findByStudGrade(String grade);
}
