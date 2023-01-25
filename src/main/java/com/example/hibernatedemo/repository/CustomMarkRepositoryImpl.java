package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.model.Mark;
import com.fasterxml.jackson.databind.ObjectMapper;
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

  private final ObjectMapper objectMapper;

  @Override
  public void save(Mark mark) {
    Query query = entityManager
        .createNativeQuery(
            "insert into my_university.student (id,fk_student_id,discipline,value) values (?,?,?,?)")
        .setParameter(2, mark.getStudent().getId())
        .setParameter(3, mark.getDiscipline())
        .setParameter(4, mark.getValue());
    query.executeUpdate();
    log.info("Mark " + mark.getDiscipline() + " was added");
  }

  @Override
  public Mark delete(int id) {
    Query query = entityManager.createQuery("delete from Mark m where m.id=?1");
    query.executeUpdate();
    return null;
  }

  @Override
  public Mark update(Mark mark) {
    Query query = entityManager
        .createQuery("update Mark m set m.discipline=" + mark.getDiscipline()
            + ", m.value=" + mark.getValue() + " where m.id=" + mark.getId());

    query.executeUpdate();
    return null;
  }
}
