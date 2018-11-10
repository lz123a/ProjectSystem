package com.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GroupController {
    @GetMapping("/group")
    public String group(){
        return "group";
    }


}
