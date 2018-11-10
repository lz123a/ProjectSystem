package com.learn.repository;

import com.learn.domain.PerGrade;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface PerGradeRepository extends JpaRepository<PerGrade,Integer> {

    public List<PerGrade> findByTeam_Name(String name);
}
