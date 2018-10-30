package com.learn.repository;

import com.learn.domain.Student;
import com.learn.domain.info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<info,Integer> {

    public void deleteById(String id);
    public info findByid(String id);
}
