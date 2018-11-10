package com.learn.repository;

import com.learn.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team,Integer> {
    public List<Team> findByClas_NameAndCourse_NameAndSemester_Name(String clas_name,String course_name,String semester_name);

    public List<Team> findByCourse_NameAndSemester_Name(String course_name,String semester_name);

    public List<Team> findByName(String name);


}
