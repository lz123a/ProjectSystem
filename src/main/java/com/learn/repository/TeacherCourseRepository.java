package com.learn.repository;

import com.learn.domain.Clas;
import com.learn.domain.Semester;
import com.learn.domain.TeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherCourseRepository extends JpaRepository<TeacherCourse,Integer> {


 //   public List<TeacherCourse> findByTeacher
      public List<TeacherCourse> findByTeacher_IdAndSemester_Id(int teacher_id,int semester_id);

  //  public List<Clas> findByTeacher_IdAndSemester_Id(int teacher_id,int semester_id);

    public List<TeacherCourse> findByTeacher_NameAndSemester_NameAndCourse_Name(String teacher_name,String semester_name,String course_name);

    public List<Semester> findBySemester_Id(int semester_id);

    public List<TeacherCourse> findByTeacher_Id(int teacher_id);

}
