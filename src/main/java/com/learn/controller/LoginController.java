package com.learn.controller;

import com.learn.domain.*;
import com.learn.interceptor.WebSecurityConfig;
import com.learn.repository.SemesterRepository;
import com.learn.repository.TeacherRepository;
import com.learn.repository.TeamRepository;
import com.learn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.learn.interceptor.WebSecurityConfig.SESSION_KEY;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    TeamRepository teamRepository;

    @GetMapping("/index")
    public String index(@SessionAttribute(SESSION_KEY) String tuid, Model model){
        model.addAttribute("name", tuid);
        List<Semester> semesters = semesterRepository.findAll();
        List<String> semesterNames = new ArrayList<>();
        for(Semester list: semesters){
            semesterNames.add(list.getName());
        }
        model.addAttribute("semesterNames",semesters);
        return  "index";
    }

    @GetMapping(value = {"/login","/"})
    public String login() {
        return "login";
    }

    @GetMapping("/stu")
    public String stu(){
        return "stu";
    }

    @GetMapping("/a")
    public String a(@SessionAttribute(SESSION_KEY)String uid,Model model) {
        List<Team> teamList = teamRepository.findByName(uid);
        Team team = teamList.get(0);
        model.addAttribute("state",team.getUploadweek());

        return "a";
    }
    @GetMapping("/b")
    public String b(@SessionAttribute(SESSION_KEY)String uid,Model model) {
        List<Team> teamList = teamRepository.findByName(uid);
        Team team = teamList.get(0);
        model.addAttribute("state",team.getUploadopen());
        model.addAttribute("state1",team.getUploadopencon());
        return "b";
    }
    @GetMapping("/c")
    public String c(@SessionAttribute(SESSION_KEY)String uid,Model model) {
        List<Team> teamList = teamRepository.findByName(uid);
        Team team = teamList.get(0);
        model.addAttribute("state",team.getUploadmid());
        model.addAttribute("state1",team.getUploadmidcon());
        return "c";
    }
    @GetMapping("/d")
    public String d(@SessionAttribute(SESSION_KEY)String uid,Model model) {
        List<Team> teamList = teamRepository.findByName(uid);
        Team team = teamList.get(0);
        model.addAttribute("state",team.getUploadend());
        model.addAttribute("state1",team.getUploadendcon());
        return "d";
    }


    @PostMapping("/loginPost")
    public @ResponseBody
    Map<String, Object> loginPost(String uid, String psword,String idenity, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        User user = new User();
        user = userRepository.findByUidAndPsword(uid,psword);
        if(user == null){
            map.put("success",false);
            map.put("message", "账号或密码错误");
            return  map;
        }else if(user.getIdentity()==1&&!idenity.equals("teacher")){
            map.put("success",false);
            map.put("message", "账号或密码错误");
            return  map;
        }else if(user.getIdentity()==0&&!idenity.equals("stu")) {
            map.put("success", false);
            map.put("message", "账号或密码错误");
            return map;
        }

        // 设置session
        if(user.getIdentity()==1){
            Teacher teacher = user.getTeacher();
            String tuid = teacher.getName();
            session.setAttribute(SESSION_KEY, tuid);
        }else{
            Team team = user.getTeam();
            String suid = team.getName();
            session.setAttribute(SESSION_KEY, suid);
        }

        map.put("success", true);
        map.put("message", "登录成功");
        return map;
    }
  /*  @PostMapping("/loginPost")
    public String login(@RequestParam String uid,@RequestParam String psword,HttpSession session){
        if (!"123456".equals(psword)) {
            return "redirect:/login";
        }
        // 设置session
        session.setAttribute(WebSecurityConfig.SESSION_KEY, uid);
        return "redirect:/index";
    }*/


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute(SESSION_KEY);
        return "redirect:/login";
    }

}
