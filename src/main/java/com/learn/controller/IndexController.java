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
    @Autowired
    PerGradeRepository perGradeRepository;
    @Autowired
    SnameRepository snameRepository;

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

    @RequestMapping("/teamPost")
    public @ResponseBody Map<String, Object> teamPost(@SessionAttribute(SESSION_KEY)String uid,String semester,String course,String clas){
        Map<String,Object> map = new HashMap<>();
        List<Team> teamList = teamRepository.findByClas_NameAndCourse_NameAndSemester_Name(clas,course,semester);
        List<String> teamName = new ArrayList<>();
        for (Team team : teamList) {
            teamName.add(team.getName());
        }
        map.put("success",true);
        map.put("message",teamName);
        return map;
    }

    @RequestMapping("/teamScorePost")
    public @ResponseBody Map<String, Object> teamScorePost(@SessionAttribute(SESSION_KEY)String uid,String semester,String course,String clas,String name){
        Map<String,Object> map = new HashMap<>();
        List<Team> teamList = teamRepository.findByName(name);
        Team team = teamList.get(0);
        List<String> canGetScoreName = new ArrayList<>();
        if(team.getUploadweek()==1){
            canGetScoreName.add("周报");
        }
        if(team.getUploadopen()==1&&team.getUploadopencon()==1){
            canGetScoreName.add("开题");
        }
        if(team.getUploadmid()==1&&team.getUploadmidcon()==1){
            canGetScoreName.add("中检");
        }
        if(team.getUploadend()==1&&team.getUploadendcon()==1){
            canGetScoreName.add("末检");
            canGetScoreName.add("报告");
        }
        CourseStandard courseStandard = courseStandardRepository.findByTeacher_NameAndCourse_NameAndSemester_NameAndStandard_Name(uid,semester,course,"版本控制工具");
        if(courseStandard!=null){
            canGetScoreName.add("版本控制工具");
        }
        canGetScoreName.add("项目名称");

        map.put("success",true);
        map.put("message",canGetScoreName);
        return map;
    }

    @RequestMapping("/changeScore")
    public @ResponseBody Map<String ,Object> changeScore(@SessionAttribute(SESSION_KEY)String uid,String name,String item,int score,String newName){
        Map<String,Object> map = new HashMap<>();
        Team team = teamRepository.findByName(name).get(0);
        List<PerGrade> perGradeList = team.getPerGrades();
        if(item.equals("开题")){
            team.setOpen_score(score);
            for (PerGrade perGrade : perGradeList) {
                perGrade.setProAll_score(perGrade.getProAll_score()-perGrade.getOpen_score()+(perGrade.getOpen_con()*score));
                perGrade.setAll_score(perGrade.getAll_score()-perGrade.getOpen_con()*score-perGrade.getOpen_score());
                perGrade.setOpen_score(perGrade.getOpen_con()*score);
                perGradeRepository.save(perGrade);
            }
            teamRepository.save(team);
        }else if(item.equals("中检")){
            team.setMid_score(score);
            for (PerGrade perGrade : perGradeList) {
                perGrade.setProAll_score(perGrade.getProAll_score()-perGrade.getMid_score()+(perGrade.getMid_con()*score));
                perGrade.setAll_score(perGrade.getAll_score()-perGrade.getMid_score()+(perGrade.getMid_con()*score));
                perGrade.setMid_score(perGrade.getMid_con()*score);
                perGradeRepository.save(perGrade);
            }
        }else if(item.equals("末检")){
            team.setEnd_score(score);
            for (PerGrade perGrade : perGradeList) {
                perGrade.setProAll_score(perGrade.getProAll_score()-perGrade.getEnd_score()+(perGrade.getEnd_con()*score));
                perGrade.setAll_score(perGrade.getAll_score()-perGrade.getEnd_score()+(perGrade.getEnd_con()*score));
                perGrade.setEnd_score(perGrade.getEnd_con()*score);
                perGradeRepository.save(perGrade);
            }
        }else if(item.equals("周报")){
            team.setWeekly_score(score);
            for (PerGrade perGrade : perGradeList) {
                perGrade.setProAll_score(perGrade.getProAll_score()-perGrade.getWeekly_score()+score);
                perGrade.setAll_score(perGrade.getAll_score()-perGrade.getWeekly_score()+score);
                perGrade.setWeekly_score(score);
                perGradeRepository.save(perGrade);
            }
        }else if(item.equals("报告")){
            team.setReport_score(score);
            for (PerGrade perGrade : perGradeList) {
                perGrade.setProAll_score(perGrade.getProAll_score()-perGrade.getReport_score()+score);
                perGrade.setAll_score(perGrade.getAll_score()-perGrade.getReport_score()+score);
                perGrade.setReport_score(score);
                perGradeRepository.save(perGrade);
            }
        }else if(item.equals("版本控制工具")){
            team.setGit_score(score);
            for (PerGrade perGrade : perGradeList) {
                perGrade.setProAll_score(perGrade.getProAll_score()-perGrade.getGit_score()+score);
                perGrade.setAll_score(perGrade.getAll_score()-perGrade.getGit_score()+score);
                perGrade.setGit_score(score);
                perGradeRepository.save(perGrade);
            }
        }else if(item.equals("项目名称")){
            team.setName(newName);
            teamRepository.save(team);
        }
        teamRepository.save(team);
        map.put("success",true);
        map.put("message","修改成功");
        return map;
    }

    @RequestMapping("/getData")
    public @ResponseBody Map<String,Object> getData(@SessionAttribute(SESSION_KEY)String uid,String clas){
        Map<String,Object> map = new HashMap<>();
        List<Sname> snames = snameRepository.findByClas_Name(clas);
        List<String> names = new ArrayList<>();
        for (Sname sname : snames) {
            names.add(sname.getName());
        }
        System.out.println(names.size());
        map.put("success",true);
        map.put("message",names);

        return map;
    }

}
