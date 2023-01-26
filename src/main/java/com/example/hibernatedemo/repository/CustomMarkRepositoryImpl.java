package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.model.Mark;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CustomMarkRepositoryImpl implements CustomMarkRepository {

    @PersistenceContext
    private final EntityManager entityManager;


    @Override
    public void add(Mark mark) {
        Query query = entityManager
                .createNativeQuery(
                        "insert into my_university.mark (fk_student_id,discipline,value) values (?,?,?)")
                .setParameter(1, mark.getStudent().getId())
                .setParameter(2, mark.getDiscipline())
                .setParameter(3, mark.getValue());
        query.executeUpdate();
    }

    @Override
    public void delete(int studentId, String discipline) {
        Query query = entityManager.createQuery("delete from Mark m where m.student.id=" + studentId + " and m.discipline like '%" + discipline + "%'");
        query.executeUpdate();
    }

    @Override
    public void update(int studentId, String discipline, float value) {
        Query query = entityManager
                .createQuery("update Mark m set m.value=" + value + " where m.student.id=" + studentId + " and m.discipline=" + discipline);

        query.executeUpdate();
    }
}
