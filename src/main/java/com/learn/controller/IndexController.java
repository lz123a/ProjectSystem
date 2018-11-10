package com.learn.controller;


import com.learn.domain.*;
import com.learn.repository.*;
import javafx.beans.binding.ObjectBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.MalformedObjectNameException;
import javax.persistence.ManyToOne;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Source;
import java.rmi.server.UID;
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
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    GradeRepository gradeRepository;
    @Autowired
    TeamRepository teamRepository;

    @RequestMapping("/getScore")
    public @ResponseBody Map<String ,Object>  getScore(@SessionAttribute(SESSION_KEY)String uid,String semester,String course,String clas){
        Map<String ,Object> map = new HashMap<>();
   //     List<List<Object>> ans = new ArrayList<>();

        List<Map<String,Object>> ans = new ArrayList<>();
        List<Team> teamList = teamRepository.findByClas_NameAndCourse_NameAndSemester_Name(clas,course,semester);
        for (Team team : teamList) {
            Map<String,Object> a = new HashMap<>();
            List<PerGrade> perGrades = team.getPerGrades();
            a.put("teamSize",perGrades.size());
            a.put("项目名称",team.getName());

            ans.add(a);
            for (PerGrade perGrade : perGrades) {
                Map<String,Object> stu = new HashMap<>();
                stu.put("项目名称",team.getName());
                stu.put("组员名单",perGrade.getName());
                stu.put("周报个人得分",perGrade.getReport_score());
                stu.put("版本控制工具个人得分",perGrade.getGit_score());
                stu.put("开题个人得分",perGrade.getOpen_score());
                stu.put("中检个人得分",perGrade.getMid_score());
                stu.put("末检个人得分",perGrade.getEnd_score());
                stu.put("报告个人得分",perGrade.getReport_score());
                stu.put("项目个人总分",perGrade.getProAll_score());
                stu.put("个人总分",perGrade.getAll_score());
                stu.put("教务处成绩",perGrade.getFinal_score());
                stu.put("满勤",perGrade.getAtten_score());
                stu.put("专业建设贡献",perGrade.getProbuild_score());
                stu.put("省级以上大赛",perGrade.getCompet_score());
                stu.put("软考",perGrade.getSoft_score());
                stu.put("减分项",perGrade.getRed_score());
                stu.put("开题",team.getOpen_score());
                stu.put("中检",team.getMid_score());
                stu.put("版本控制工具",team.getGit_score());
                stu.put("末检",team.getEnd_score());
                stu.put("周报",team.getWeekly_score());
                stu.put("报告",team.getReport_score());
                ans.add(stu);
            }
        }
        System.out.println(semester+course+clas);
        map.put("success",true);
        map.put("ans",ans);
        //   System.out.println(teamList.size());
        //map.put("teamlist",teamList);
        return map;
    }

    @RequestMapping("/addCourse")
    public @ResponseBody Map<String,Object> addCourse(@SessionAttribute(SESSION_KEY)String uid,String semester,String grade,String courseName){
        Map<String,Object> map = new HashMap<>();
        Course course = courseRepository.findByName(courseName);
        if(course==null){
            Course course1 = new Course();
            course.setName(courseName);
            course = course1;
            courseRepository.save(course1);
        }

        Teacher teacher = teacherRepository.findByName(uid);
        Grade grade1 = gradeRepository.findByName(grade);
        List<Clas> clasList = grade1.getClas();
        Semester semester1 = semesterRepository.findByName(semester);
        for (Clas clas : clasList) {
            TeacherCourse teacherCourse = new TeacherCourse();
            teacherCourse.setClas(clas);
            teacherCourse.setCourse(course);
            teacherCourse.setSemester(semester1);
            teacherCourse.setTeacher(teacher);
            teacherCourseRepository.save(teacherCourse);
        }
        map.put("success",true);
        map.put("message","成功添加");
        return map;
    }

    @GetMapping("/choice")
    public String choice(@SessionAttribute(SESSION_KEY) String tuid,String semester,String course, Model model){
        model.addAttribute("name", tuid);
        List<CourseStandard> standardList = courseStandardRepository.findByTeacher_NameAndSemester_NameAndCourse_Name(tuid,semester,course);
       // List<CourseStandard> standardList = courseStandardRepository.findByTIDAndCIDAndSID(teacherRepository.findByName(tuid).getId(),courseRepository.findByName(course).getId(),semesterRepository.findByName(semester).getId());

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
