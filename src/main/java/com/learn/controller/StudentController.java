package com.learn.controller;


import com.learn.domain.PerGrade;
import com.learn.domain.Student;
import com.learn.domain.Team;
import com.learn.repository.PerGradeRepository;
import com.learn.repository.StudentRepository;
import com.learn.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.jws.Oneway;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.*;

import static com.learn.interceptor.WebSecurityConfig.SESSION_KEY;

@Controller
public class StudentController {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PerGradeRepository perGradeRepository;

    @RequestMapping("/subCon")
    public @ResponseBody
    Map<String,Object> subCon(@SessionAttribute(SESSION_KEY)String uid,String conStr, int val){
        Map<String,Object> map = new HashMap<>();
        List<Team> teamList = teamRepository.findByName(uid);
        Team team = teamList.get(0);
        List<PerGrade> perGradeList = team.getPerGrades();
  //      System.out.println(val);
        if(val==1){
            team.setUploadopencon(1);
        }else if(val==2){
            team.setUploadmidcon(1);
        }else if(val==3){
            team.setUploadendcon(1);
        }
        conStr = " "+conStr+" ";
        String []studentNames = conStr.split(" ");
        float con = 1;
        for (String studentName : studentNames) {
            if(studentName.equals("")){
                continue;
            }

            for (PerGrade perGrade : perGradeList) {
           //     System.out.println(perGrade.getName()+"  "+studentName);
                if(perGrade.getName().equals(studentName)){//System.out.println(studentName+" yes");
                    if(val==1){
                        perGrade.setOpen_con(con);
                    }else if(val==2){
                        perGrade.setMid_con(con);
                    }else if(val==3){
                        perGrade.setEnd_con(con);
                    }
                    perGradeRepository.save(perGrade);
                    break;
                }
            }
            con = con - 0.1f;
        }
        teamRepository.save(team);
        map.put("success",true);
        map.put("message","上传贡献表成功");
        return map;
    }

    /*@Autowired
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
*/

}
