package com.learn.repository;

import com.learn.domain.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SemesterRepository extends JpaRepository<Semester,Integer> {
    public List<Semester> findAll();
 //   public List<Semester> findAll();
    public Semester findByName(String name);
}
