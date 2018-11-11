package com.learn.repository;

import com.learn.domain.Clas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClasRepository extends JpaRepository<Clas,Integer> {
    public Clas findByName(String name);
}
