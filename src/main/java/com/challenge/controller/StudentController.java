package com.challenge.controller;

import com.challenge.dto.student.StudentCreateRequest;
import com.challenge.dto.student.StudentResponse;
import com.challenge.model.Student;
import com.challenge.service.student.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponse createStudent(@Valid @RequestBody StudentCreateRequest request) {

        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setAdmin(request.isAdmin());

        Student saved = studentService.createStudent(student);

        return new StudentResponse(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.isAdmin()
        );
    }

    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {
        Student s = studentService.getStudentById(id);
        return new StudentResponse(
                s.getId(),
                s.getName(),
                s.getEmail(),
                s.isAdmin()
        );
    }

    @GetMapping("/email/{email}")
    public StudentResponse getStudentByEmail(@PathVariable String email) {
        Student s = studentService.getStudentByEmail(email);
        return new StudentResponse(
                s.getId(),
                s.getName(),
                s.getEmail(),
                s.isAdmin()
        );
    }
}
