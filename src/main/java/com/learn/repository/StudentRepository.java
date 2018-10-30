package com.learn.repository;

import com.learn.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

    public interface StudentRepository extends JpaRepository<Student,Integer> {

    //通过年龄查询
    public List<Student> findByAge(Integer age);


}
