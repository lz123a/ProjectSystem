package com.learn.controller;


import com.learn.domain.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

   /* @Value("${cupSize}")
    private String cupSize;
    @Value("${age}")
    private Integer age;*/

    @Autowired
    private People people;


   /* public String say(@PathVariable("age") Integer id){
        return "age: "+id;
       // return people.getName() + ' ' + people.getAge();
    }*/

    @RequestMapping(value="/hello",method = RequestMethod.GET)
    public String say(@RequestParam("id") Integer id){
        return "age: "+id;
        // return people.getName() + ' ' + people.getAge();
    }
}
