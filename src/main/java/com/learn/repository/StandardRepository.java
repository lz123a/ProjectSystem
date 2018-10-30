package com.learn.repository;

import com.learn.domain.Standard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandardRepository  extends JpaRepository<Standard,Integer>{
    public Standard findByName(String name);

}
