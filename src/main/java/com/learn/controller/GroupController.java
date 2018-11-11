package com.learn.controller;

import com.learn.domain.*;
import com.learn.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.repository.ClassRepository;

import javax.websocket.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.learn.interceptor.WebSecurityConfig.SESSION_KEY;

@Controller
public class GroupController {

    @Autowired
    SnameRepository snameRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PerGradeRepository perGradeRepository;
    @Autowired
    ClasRepository clasRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    SemesterRepository semesterRepository;

    @GetMapping("/group")
    public String group(@SessionAttribute(SESSION_KEY)String uid, String clas, String course, String semester, Model model){
        List<Sname> snames = snameRepository.findByClas_Name(clas);
        List<String> names = new ArrayList<>();
        for (Sname sname : snames) {
            names.add(sname.getName());
        }
        model.addAttribute("names",names);
        return "group";
    }

    @RequestMapping("/saveGroup")
    public @ResponseBody
    Map<String,Object> getGroup(@SessionAttribute(SESSION_KEY)String uid, String group, String course, String semester, String clas){
        Map<String,Object> map = new HashMap<>();
        Clas clas1 = clasRepository.findByName(clas);
        Semester semester1 = semesterRepository.findByName(semester);
        Course course1 = courseRepository.findByName(course);
        System.out.println(group);
        String []g1 = group.split("@");
        int i=1;
        for (String s : g1) {
            if(s.equals("")){
                continue;
            }
            String []gro = s.split("&");
            Team team = new Team();
            team.setClas(clas1);
            team.setCourse(course1);
            team.setSemester(semester1);
            team.setName(clas1.getName()+"未命名"+String.valueOf(i));
            teamRepository.save(team);
            Team team1 = teamRepository.findByName(clas1.getName()+"未命名"+String.valueOf(i)).get(0);
            User user = new User();
            user.setIdentity(0);
            user.setPsword("123456");
            user.setTeam(team1);
            user.setUid(clas1.getName()+course1.getId()+"0"+i);
            userRepository.save(user);
            for (String gro1 : gro) {
                if(gro1.equals("")){
                    continue;
                }
                PerGrade perGrade = new PerGrade();
                perGrade.setTeam(team1);
                perGrade.setName(gro1);
                perGrade.setClas(clas1);
                perGrade.setCourse(course1);
                perGrade.setFinal_score("及格");
                perGradeRepository.save(perGrade);
            }
            i++;
        }
        map.put("success",true);
        map.put("message","成功");
        return map;
    }


}
