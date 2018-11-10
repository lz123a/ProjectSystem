package com.learn.repository;

import com.learn.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade,Integer> {
    public Grade findByName(String name);
}
