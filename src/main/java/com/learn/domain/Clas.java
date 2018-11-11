package com.learn.domain;


import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "class")
public class Clas {
    @Id
    @GeneratedValue
    private int id;

    private String name;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grade_id")
    private Grade grade;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clas")
    private List<Team> teams;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "clas")
    private List<PerGrade> perGrades;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clas")
    private  List<TeacherCourse> teacherCourses;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clas")
    private  List<Sname> snames;

    public Clas(){

    }

    public List<Sname> getSnames() {
        return snames;
    }

    public void setSnames(List<Sname> snames) {
        this.snames = snames;
    }

    public List<PerGrade> getPerGrades() {
        return perGrades;
    }

    public void setPerGrades(List<PerGrade> perGrades) {
        this.perGrades = perGrades;
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

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
