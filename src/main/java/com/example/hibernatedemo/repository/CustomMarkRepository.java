package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.model.Mark;

public interface CustomMarkRepository {
  void save(Mark mark);
  Mark delete(int id);
  Mark update(Mark mark);
}
