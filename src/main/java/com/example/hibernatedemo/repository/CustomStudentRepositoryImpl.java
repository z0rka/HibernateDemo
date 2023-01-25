package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CustomStudentRepositoryImpl implements CustomStudentRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    private final ObjectMapper objectMapper;

    @Override
    public void save(Student student) {
        Query query = entityManager
                .createNativeQuery("insert into my_university.student (id,name,email) values (?,?,?)")
                .setParameter(2, student.getName())
                .setParameter(3, student.getEmail());
        query.executeUpdate();
        log.info("Student " + student.getName() + " was added");
    }

    @Override
    public Student delete(int studentId) {
        Query query = entityManager.createQuery("delete from Student s where s.id=?1");
        query.executeUpdate();
        return null;
    }

    @Override
    public Student update(Student student) {
        Query query = entityManager
                .createQuery("update Student s set s.name=" + student.getName()
                        + ", s.email=" + student.getEmail() + " where s.id=" + student.getId());

        query.executeUpdate();
        return null;
    }

    @Override
    public Student getById(int id) {
        Query query = entityManager.createNativeQuery("select student from my_university.student where id=?1");
        Object singleResult = query.getSingleResult();
        return (Student) singleResult;
    }

    @Override
    public List getAll() {
        Query query = entityManager.createNativeQuery("select * from my_university.student");
        return query.getResultList();
    }
}
