package com.learn.repository;

import com.learn.domain.Sname;
import org.hibernate.boot.model.source.spi.JpaCallbackSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SnameRepository extends JpaRepository<Sname,Integer> {

    public List<Sname> findByClas_Name(String name);

}
