package com.learn.controller;


import com.learn.domain.Student;
import com.learn.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    //查询列表
    @GetMapping(value="/students")
    public List<Student> stuList(){
        return studentRepository.findAll();
    }

    //新增学生
    @PostMapping(value = "/students")
    public Student stuAdd(@Valid Student student, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return  null;
        }
        student.setName(student.getName());
        student.setAge(student.getAge());
        return studentRepository.save(student);
    }


    //查询学生
    @GetMapping(value = "/students/{id}")
    public Student stuSel(@PathVariable("id") Integer id){
        return studentRepository.findOne(id);
    }

    @GetMapping(value = "/students/age/{age}")
    public List<Student> stuByage(@PathVariable("age") Integer age){
        return studentRepository.findByAge(age);
    }


    //修改学生
    @PutMapping(value = "/students/{id}")
    public Student stuUpdate(@PathVariable("id") Integer id,
                             @RequestParam("name") String name,
                             @RequestParam("age") Integer age){
        Student student = new Student();
        student.setAge(age);
        student.setStuId(id);
        student.setName(name);
        return studentRepository.save(student);
    }

    //删除学生
    @DeleteMapping(value = "/students/{id}")
    public void stuDel(@PathVariable("id") Integer id){
        studentRepository.delete(id);
    }


}
