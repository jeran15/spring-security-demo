package com.jeran.spring_.security_demo.controller;

import com.jeran.spring_.security_demo.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    List<Student> students = new ArrayList<>(List.of(
            new Student(1,"Jeran","Java"),
            new Student(2,"Diluxshikan","Python"),
            new Student(3,"Gobi","AI"),
            new Student(4,"Sajeeth", "PHP")
    ));

    @GetMapping("csrf-Token")
    public CsrfToken getcsrfTokern(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }


    @GetMapping("students")
    public List<Student> getStudents(){
        return students;
    }

    @PostMapping("students")
    public void addStudent(@RequestBody Student student){
        students.add(student);
    }
}
