package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.model.Student;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomStudentRepository {
    void add(Student student);
    void delete(@Param("id") int id);
    void update(int id, String name, String email);
    Student getById(int id);
    List<Student> getAll();
}
