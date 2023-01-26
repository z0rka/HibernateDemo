package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository extends JpaRepository<Mark,Integer> , CustomMarkRepository{
}
