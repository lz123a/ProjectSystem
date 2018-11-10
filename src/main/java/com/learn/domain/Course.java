package com.learn.domain;


import javax.persistence.*;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Team> teams;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
    private List<PerGrade> perGrades;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private  List<TeacherCourse> teacherCourses;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<CourseStandard> courseStandards;

    public List<TeacherCourse> getTeacherCourses() {
        return teacherCourses;
    }

    public void setTeacherCourses(List<TeacherCourse> teacherCourses) {
        this.teacherCourses = teacherCourses;
    }

    public Course(){

    }

    public List<PerGrade> getPerGrades() {
        return perGrades;
    }

    public void setPerGrades(List<PerGrade> perGrades) {
        this.perGrades = perGrades;
    }

    public List<CourseStandard> getCourseStandards() {
        return courseStandards;
    }

    public void setCourseStandards(List<CourseStandard> courseStandards) {
        this.courseStandards = courseStandards;
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
