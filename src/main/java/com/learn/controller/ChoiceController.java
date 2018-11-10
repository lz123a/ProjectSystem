package com.learn.controller;

import com.learn.domain.*;
import com.learn.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.learn.interceptor.WebSecurityConfig.SESSION_KEY;

@Controller
public class ChoiceController {

    @Autowired
    SemesterRepository semesterRepository;
    @Autowired
    CourseStandardRepository courseStandardRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StandardRepository standardRepository;
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PerGradeRepository perGradeRepository;

    List<CourseStandard> courseStandards;
    List<String> standardNames;


    @PostMapping("/deleteStandard")
    public @ResponseBody
    Map<String ,Object> deleteStandard(@SessionAttribute(SESSION_KEY)String tuid,String semester_name,String course_name){
        Map<String,Object> map = new HashMap<>();
        courseStandards = courseStandardRepository.findByTeacher_NameAndSemester_NameAndCourse_Name(tuid,semester_name,course_name);
        standardNames = new ArrayList<>();
        for (CourseStandard standard : courseStandards) {
            standardNames.add(standard.getStandard().getName());
            System.out.println(standard.getStandard().getName()+"  d");
        }
        courseStandardRepository.deleteByTIdAndCIdAndSId(teacherRepository.findByName(tuid).getId(),courseRepository.findByName(course_name).getId(),semesterRepository.findByName(semester_name).getId());
        map.put("success",true);
        map.put("message","成功");
        return map;
    }

    @PostMapping("/saveChangeStandard")
    public @ResponseBody
    Map<String ,Object> saveChangeStandard(@SessionAttribute(SESSION_KEY)String tuid,String standards,String semester_name,String course_name){
        Map<String,Object> map = new HashMap<>();
        System.out.println(tuid+"    "+semester_name+"   "+course_name);
        System.out.println(standards);
 //       long count = courseStandardRepository.deleteByTeacher_NameAndCourse_NameAndSemester_Name(tuid,course_name,semester_name);
  //      courseStandardRepository.flush();
    //    courseStandardRepository.deleteByTIdAndCIdAndSId(teacherRepository.findByName(tuid).getId(),courseRepository.findByName(course_name).getId(),semesterRepository.findByName(semester_name).getId());
//        System.out.println(courseStandards.size());
        CourseStandard courseStandard = new CourseStandard();

        Teacher teacher = teacherRepository.findByName(tuid);

        Course course = courseRepository.findByName(course_name);

        Semester semester = semesterRepository.findByName(semester_name);


   //     Standard standard = new Standard();
        String [] standardName = standards.split("&&");
        Map<String,Integer> map1 = new HashMap<>();
        for(String s:standardName){
            if(s.equals(""))
                continue;
            map1.put(s,1);
            System.out.println(s);
            CourseStandard courseStandard1 = new CourseStandard();
            courseStandard1.setTeacher(teacher);
            courseStandard1.setCourse(course);
            courseStandard1.setSemester(semester);
            /*if(standardRepository.findByName(s) == null){
                Standard standard1 = new Standard();
                standard1.setCategory(0);
                standard1.setName(s);
            }*/
            Standard standard = standardRepository.findByName(s);
            courseStandard1.setStandard(standard);
            courseStandardRepository.save(courseStandard1);
        }
        System.out.println(standardNames.size());
        System.out.println(map1.size());
        for (String name : standardNames) {
            if(map1.containsKey(name)){
                map1.remove(name);
            }else{
                map1.put(name,1);
            }
        }
        System.out.println(map1.size()+" mapsize");
        if(map1.size()==0){
            map.put("success",true);
            map.put("message","修改成功");
            return map;
        }
        List<Team> teamList = teamRepository.findByCourse_NameAndSemester_Name(course_name,semester_name);

        for (String s : map1.keySet()) {
            System.out.println(s);
            if(s.equals("版本控制工具")){
                for (Team team : teamList) {
                    team.setGit_score(0);
                    List<PerGrade> perGradeList = team.getPerGrades();
                    teamRepository.save(team);
                    for (PerGrade perGrade : perGradeList) {
                        perGrade.setGit_score(0);
                        perGradeRepository.save(perGrade);
                    }
                }
            }else if(s.equals("周报")){
                for (Team team : teamList) {
                    team.setWeekly_score(0);
                    List<PerGrade> perGradeList = team.getPerGrades();
                    teamRepository.save(team);
                    for (PerGrade perGrade : perGradeList) {
                        perGrade.setWeekly_score(0);
                        perGradeRepository.save(perGrade);
                    }
                }
            }else if(s.equals("中检")){
                for (Team team : teamList) {
                    team.setMid_score(0);
                    List<PerGrade> perGradeList = team.getPerGrades();
                    teamRepository.save(team);
                    for (PerGrade perGrade : perGradeList) {
                        perGrade.setMid_score(0);
                        perGradeRepository.save(perGrade);
                    }
                }
            }else if(s.equals("满勤")){
                for (Team team : teamList) {
                    List<PerGrade> perGradeList = team.getPerGrades();
                    for (PerGrade perGrade : perGradeList) {
                        perGrade.setAtten_score(0);
                        perGradeRepository.save(perGrade);
                    }
                }
            }
            else if(s.equals("软考")){
                for (Team team : teamList) {
                    List<PerGrade> perGradeList = team.getPerGrades();
                    for (PerGrade perGrade : perGradeList) {
                        perGrade.setSoft_score(0);
                        perGradeRepository.save(perGrade);
                    }
                }
            }else if(s.equals("专业建设贡献")){
                for (Team team : teamList) {
                    List<PerGrade> perGradeList = team.getPerGrades();
                    for (PerGrade perGrade : perGradeList) {
                        perGrade.setProbuild_score(0);
                        perGradeRepository.save(perGrade);
                    }
                }
            }
        }

        map.put("success",true);
        map.put("message","修改成功");

        return map;
    }

    @PostMapping("/initStandard")
    public @ResponseBody
    Map<String, Object> initStandard(@SessionAttribute(SESSION_KEY)String tuid, String semester_name, String course_name ){
        Map<String ,Object> map = new HashMap<>();
        System.out.println(semester_name+"   "+course_name);
        List<CourseStandard> standardList = courseStandardRepository.findByTeacher_NameAndSemester_NameAndCourse_Name(tuid,semester_name,course_name);
     //   List<CourseStandard> standardList = courseStandardRepository.findByTIDAndCIDAndSID(teacherRepository.findByName(tuid).getId(),courseRepository.findByName(course_name).getId(),semesterRepository.findByName(semester_name).getId());
        List<String> standardNameList = new ArrayList<>();
        for(CourseStandard list: standardList){
            standardNameList.add(list.getStandard().getName());
            System.out.println(list.getStandard().getName());
        }
        map.put("success",true);
        map.put("standardNum",standardNameList.size());
        map.put("message",standardNameList);
        return  map;
    }
}
