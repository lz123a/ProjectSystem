package com.learn.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Teacher {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private List<TeacherCourse> teacherCourses;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private List<CourseStandard> courseStandards;

    public Teacher(){

    }

    public List<CourseStandard> getCourseStandards() {
        return courseStandards;
    }

    public void setCourseStandards(List<CourseStandard> courseStandards) {
        this.courseStandards = courseStandards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TeacherCourse> getTeacherCourses() {
        return teacherCourses;
    }

    public void setTeacherCourses(List<TeacherCourse> teacherCourses) {
        this.teacherCourses = teacherCourses;
    }
}
