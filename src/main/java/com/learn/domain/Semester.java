package com.learn.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Semester {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "semester")
    private List<Team> teams;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "semester")
    private  List<TeacherCourse> teacherCourses;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "semester")
    private List<CourseStandard> courseStandards;

    public Semester(){

    }


    public List<CourseStandard> getCourseStandards() {
        return courseStandards;
    }

    public void setCourseStandards(List<CourseStandard> courseStandards) {
        this.courseStandards = courseStandards;
    }

    public List<TeacherCourse> getTeacherCourses() {
        return teacherCourses;
    }

    public void setTeacherCourses(List<TeacherCourse> teacherCourses) {
        this.teacherCourses = teacherCourses;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
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
}
