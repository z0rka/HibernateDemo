package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>, CustomStudentRepository {
}
