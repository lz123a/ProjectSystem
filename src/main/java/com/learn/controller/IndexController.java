package com.learn.controller;


import com.learn.domain.*;
import com.learn.repository.CourseStandardRepository;
import com.learn.repository.SemesterRepository;
import com.learn.repository.TeacherCourseRepository;
import com.learn.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.management.MalformedObjectNameException;
import javax.persistence.ManyToOne;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Source;
import java.util.*;

import static com.learn.interceptor.WebSecurityConfig.SESSION_KEY;

@Controller
public class IndexController {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    SemesterRepository semesterRepository;
    @Autowired
    TeacherCourseRepository teacherCourseRepository;

    @Autowired
    CourseStandardRepository courseStandardRepository;

    @GetMapping("/choice")
    public String choice(@SessionAttribute(SESSION_KEY) String tuid,String semester,String course, Model model){
        model.addAttribute("name", tuid);
        List<CourseStandard> standardList = courseStandardRepository.findByTeacher_NameAndSemester_NameAndCourse_Name(tuid,semester,course);
        List<String> standardNameList = new ArrayList<>();
        for(CourseStandard list: standardList){
            standardNameList.add(list.getStandard().getName());
 //           System.out.println(list.getStandard().getName());
        }
        
        model.addAttribute("standardNames",standardNameList);
        System.out.println(semester+"  bbb "+course);
        return  "choice";
    }

    @PostMapping("/standardTableHead")
    public @ResponseBody
        Map<String ,Object> standardTableHead(@SessionAttribute(SESSION_KEY)String tuid,String semester_name, String course_name ){
        Map<String,Object> map = new HashMap<>();
        List<CourseStandard> standardList = courseStandardRepository.findByTeacher_NameAndSemester_NameAndCourse_Name(tuid,semester_name,course_name);
        List<String> standardNameList = new ArrayList<>();
        List<Integer> standardCategoryList = new ArrayList<>();
        int addCount = 0;
        for(CourseStandard list: standardList){
            standardNameList.add(list.getStandard().getName());
            if(list.getStandard().getCategory() == 1){
                addCount++;
            }
            standardCategoryList.add(list.getStandard().getCategory());
        }
        map.put("success",true);
        map.put("standardNameList",standardNameList);
        map.put("standardCategoryList",standardCategoryList);
        map.put("addCount",addCount);

        return map;
    }

   /* @PostMapping("/changeStandard")
    public String changeStandard(@SessionAttribute(SESSION_KEY)String tuid,String semester_name, String course_name ,Model model,HttpServletResponse response) {
    //    Map<String,Object> map = new HashMap<>();
        List<CourseStandard> standardList = courseStandardRepository.findByTeacher_NameAndSemester_NameAndCourse_Name(tuid,semester_name,course_name);
        //      model.addAttribute("standard",standardList);
        System.out.println(standardList.size());

        return "redirect:/choice";
    }*/

    @PostMapping("/changeStandard")
    public @ResponseBody
        Map<String, Object>changeStandard(@SessionAttribute(SESSION_KEY)String tuid,String semester_name, String course_name ,Model model,HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        List<CourseStandard> standardList = courseStandardRepository.findByTeacher_NameAndSemester_NameAndCourse_Name(tuid,semester_name,course_name);
  //      model.addAttribute("standard",standardList);
 //       System.out.println(standardList.size());

        map.put("success",true);
        map.put("message","成功");
        return map;
    }

    @PostMapping("/semesterPost")
    public @ResponseBody
    Map<String, Object> semesterPost(@SessionAttribute(SESSION_KEY)String tuid) {
        List<Semester> semesterList = semesterRepository.findAll();
        Map<String, Object> map = new HashMap<>();
        map.put("semester",semesterList);
        return map;
    }

    @PostMapping("/clasPost")
    public @ResponseBody
    Map<String,Object> clasPost(@SessionAttribute(SESSION_KEY)String tuid,String semester_name, String course_name){
        Map<String,Object> map = new HashMap<>();
    //    System.out.println(semester_name+"  "+course_name);
        List<TeacherCourse> teacherCourseList = teacherCourseRepository.findByTeacher_NameAndSemester_NameAndCourse_Name(tuid,semester_name,course_name);

        if(teacherCourseList.size()==0){
            map.put("success",false);
            map.put("message","查询结果为空");
        }
        List<String> clasNameList = new ArrayList<>();
        for(TeacherCourse list: teacherCourseList){
            Clas clas = list.getClas();
            clasNameList.add(clas.getName());
        }

        HashSet h = new HashSet(clasNameList);
        clasNameList.clear();
        clasNameList.addAll(h);
        Collections.sort(clasNameList);
        map.put("success",true);
        map.put("message",clasNameList);
        return map;
    }

    @PostMapping("/coursePost")
    public @ResponseBody
    Map<String, Object> coursePost(@SessionAttribute(SESSION_KEY)String tuid, String semester_name, String grade_name) {
        System.out.println(tuid+"   "+semester_name+"   "+grade_name);
        Map<String, Object> map = new HashMap<>();
        Teacher teacher = teacherRepository.findByName(tuid);
        Semester semester = semesterRepository.findByName(semester_name);
        System.out.println(teacher.getId()+"   bb   "+semester.getId()+"   aaaa");
        List<TeacherCourse> teacherCourseList1 = teacherCourseRepository.findByTeacher_Id(teacher.getId());
/*        for(TeacherCourse list1: teacherCourseList1){
            System.out.println(list1.getId());
        }*/
        List<TeacherCourse> teacherCourseList = teacherCourseRepository.findByTeacher_IdAndSemester_Id(teacher.getId(),semester.getId());
        List<String> courseNameList = new ArrayList();

        for(TeacherCourse list1: teacherCourseList){
     //       System.out.println(list1.getId()  );
            Clas clas = list1.getClas();
     //       System.out.println(clas.getName());
     //       System.out.println(clas.getId());
            Grade grade = clas.getGrade();
            if(grade_name.equals(grade.getName())){
                Course course = list1.getCourse();
                courseNameList.add(course.getName());
            }
        }
        HashSet h = new HashSet(courseNameList);
        courseNameList.clear();
        courseNameList.addAll(h);
        map.put("success",true);
        map.put("course",courseNameList);
        return  map;
    }

}
