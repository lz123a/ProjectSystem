package com.learn.controller;


import com.learn.domain.info;
import com.learn.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ManagementController {

    @Autowired
    InfoRepository repository;

    @GetMapping(value = "/findInfo")
    public List<info> infoList(){
        return repository.findAll();
    }

    @PostMapping(value = "/addInfo")
    public void infoAdd(@Valid info i){
        i.setId(i.getId());
        i.setName(i.getName());
        i.setCourse_name(i.getCourse_name());
        i.setPro_name(i.getPro_name());
        repository.save(i);
    }
    @PutMapping(value = "/updateInfo")
    public void infoUpdate(@RequestParam("id") String id,
                           @RequestParam("name") String name,
                           @RequestParam("pro_name") String pro_name,
                           @RequestParam("course_name") String course_name){
        info info = new info();
        info.setId(id);
        info.setName(name);
        info.setPro_name(pro_name);
        info.setCourse_name(course_name);
        repository.save(info);
    }

    @Transactional
    @DeleteMapping(value = "/deleteInfo/{id}")
    public void infoDelete(@PathVariable("id") String id){
    //    info info = repository.findByid(id);
        repository.deleteById(id);
    }
}
