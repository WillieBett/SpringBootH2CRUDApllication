package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.IStudentRepository;
import com.example.demo.model.Student;

@RestController
public class StudentController {

    @Autowired
    private IStudentRepository repository;

    @PostMapping("/saveStudent")
    public String saveStudent(@RequestBody Student Student) {
        repository.save(Student);
        return "Student record saved successfully ,Name : " + Student.getStudName() ;
    }

    @GetMapping("/getAllStudents")
    public List<Student> getAll() {
        return repository.findAll();
    }

    @GetMapping("/getStudent/{grade}")
    public List<Student> getStudentsByGrade(@PathVariable String grade) {
        return repository.findByStudGrade(grade);
    }

    @DeleteMapping("/removeStudent/{studId}")
    public String removeStudent(@PathVariable int studId) {
        repository.deleteById(studId);
        return "Student record deleted with Student id: " + studId;
    }

    @PutMapping("/updateStudent/{studId}")
    public Student updateStudentDetails(@RequestBody Student updateStudentRequest, @PathVariable int studId) {
        Student Student = repository.findById(studId).get();
        Student.setStudId(updateStudentRequest.getStudId());
        Student.setStudName(updateStudentRequest.getStudName());
        Student.setStudGrade(updateStudentRequest.getStudGrade());
        Student.setStudFabSubject(updateStudentRequest.getStudFabSubject());

        repository.saveAndFlush(Student);

        return Student;
    }
}
