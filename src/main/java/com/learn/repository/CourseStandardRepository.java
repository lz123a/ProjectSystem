package com.learn.repository;

import com.learn.domain.CourseStandard;
import com.learn.domain.Standard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CourseStandardRepository extends JpaRepository<CourseStandard,Integer> {
 //   public List<CourseStandard> findByTeacher_NameAndSemester_NameAndCourse_Name(String teacher_name,String Semester_name,String course_name);

 //   @Query("select new ")

    @Transactional
    @Modifying
    @Query("delete from CourseStandard where teacher_id = ?1 and course_id = ?2 and semester_id = ?3")
    public void  deleteByTIdAndCIdAndSId(int teacher_id,int course_id,int semester_id);

    @Transactional
    @Modifying
    @Query("select '*' from CourseStandard where teacher_id = ?1 and course_id = ?2 and semester_id = ?3 order by standard_id")
    public List<CourseStandard> findByTIDAndCIDAndSID(int teacher_id,int course_id,int semester_id);

    public long deleteByTeacher_NameAndCourse_NameAndSemester_Name(String teacher_name,String course_name,String semester_name);

    public long removeByTeacher_NameAndCourse_NameAndSemester_Name(String teacher_name,String semester_name,String course_name);

    public List<CourseStandard> findByTeacher_NameAndSemester_NameAndCourse_Name(String teacher_name,String Semester_name,String course_name);

}
