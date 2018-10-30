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

    @PostMapping("/deleteStandard")
    public @ResponseBody
    Map<String ,Object> deleteStandard(@SessionAttribute(SESSION_KEY)String tuid,String semester_name,String course_name){
        Map<String,Object> map = new HashMap<>();
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

        for(String s:standardName){
            if(s.equals(""))
                continue;
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
