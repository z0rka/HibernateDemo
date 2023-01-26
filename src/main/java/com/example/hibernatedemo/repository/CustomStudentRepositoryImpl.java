package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CustomStudentRepositoryImpl implements CustomStudentRepository {

    @PersistenceContext
    private final EntityManager entityManager;


    @Override
    public void add(Student student) {
        Query query = entityManager
                .createNativeQuery("insert into my_university.student (name,email)values (?,?)")
                .setParameter(1, student.getName())
                .setParameter(2, student.getEmail());
        query.executeUpdate();
        log.info("Student " + student.getName() + " was added");
    }

    @Modifying
    @Override
    public void delete(int studentId) {
        Query query1 = entityManager.createNativeQuery("delete from my_university.mark where fk_student_id=" + studentId);
        Query query2 = entityManager.createNativeQuery("delete from my_university.student where id =" + studentId);

        query1.executeUpdate();
        query2.executeUpdate();
    }

    @Modifying
    @Override
    public void update(int id, String name, String email) {
        Query query = entityManager
                .createNativeQuery( "update my_university.student set name= '" + name
                        + "', email='" + email + "'  where id=" + id);

        query.executeUpdate();
    }


    @Override
    public List<Student> getAll() {
        Query query = entityManager.createQuery("select s from Student s");
        return  query.getResultList();
    }

    @Override
    public Student getById(int id) {
        Query query = entityManager.createQuery("select s from Student s where s.id=" + id);
        Object singleResult;
        try {
            singleResult = query.getSingleResult();
        }catch (NoResultException e){
            singleResult = null;
        }
        return (Student) singleResult;
    }

}
