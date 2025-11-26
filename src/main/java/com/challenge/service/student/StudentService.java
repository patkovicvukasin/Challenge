package com.challenge.service.student;

import com.challenge.model.Student;

public interface StudentService {

    Student createStudent(Student student);

    Student getStudentById(Long id);

    Student getStudentByEmail(String email);
}

