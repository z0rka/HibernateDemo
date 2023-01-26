package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.model.Mark;
import org.springframework.transaction.annotation.Transactional;

public interface CustomMarkRepository {
    @Transactional
    void add(Mark mark);

    void delete(int studentId, String discipline);

    void update(int studentId, String discipline, float value);

}
