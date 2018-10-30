package com.learn.repository;

import com.learn.domain.Teacher;
import com.learn.domain.info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
  //  public Teacher findBy
    public Teacher findByName(String name);

}
