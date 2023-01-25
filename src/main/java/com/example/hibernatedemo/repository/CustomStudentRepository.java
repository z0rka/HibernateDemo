package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.model.Student;

import java.util.List;

public interface CustomStudentRepository {
    void save(Student student);
    Student delete(int id);
    Student update(Student student);
    Student getById(int id);
    List<Student> getAll();
}
