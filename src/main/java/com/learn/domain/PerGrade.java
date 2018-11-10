package com.learn.domain;

import jdk.Exported;

import javax.persistence.*;

@Entity
@Table(name = "stu_grade")
public class PerGrade {
    @Id
    @GeneratedValue
    private int id;

    private String stu_id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clas_id")
    private Clas clas;

    private float proAll_score;

    private float open_con;

    private float open_score;

    private float mid_con;

    private float mid_score;

    private float end_con;

    private float end_score;

    private float report_score;

    private float weekly_score;

    private float add_score;

    private float red_score;

    private float all_score;

    private float git_score;

    private String final_score;

    private float atten_score;

    private float probuild_score;

    private float compet_score;

    private float soft_score;

    public PerGrade(){

    }

    public float getProAll_score() {
        return proAll_score;
    }

    public void setProAll_score(float proAll_score) {
        this.proAll_score = proAll_score;
    }

    public float getAtten_score() {
        return atten_score;
    }

    public void setAtten_score(float atten_score) {
        this.atten_score = atten_score;
    }

    public float getProbuild_score() {
        return probuild_score;
    }

    public void setProbuild_score(float probuild_score) {
        this.probuild_score = probuild_score;
    }

    public float getCompet_score() {
        return compet_score;
    }

    public void setCompet_score(float compet_score) {
        this.compet_score = compet_score;
    }

    public float getSoft_score() {
        return soft_score;
    }

    public void setSoft_score(float soft_score) {
        this.soft_score = soft_score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Clas getClas() {
        return clas;
    }

    public void setClas(Clas clas) {
        this.clas = clas;
    }

    public float getOpen_con() {
        return open_con;
    }

    public void setOpen_con(float open_con) {
        this.open_con = open_con;
    }

    public float getOpen_score() {
        return open_score;
    }

    public void setOpen_score(float open_score) {
        this.open_score = open_score;
    }

    public float getMid_con() {
        return mid_con;
    }

    public void setMid_con(float mid_con) {
        this.mid_con = mid_con;
    }

    public float getMid_score() {
        return mid_score;
    }

    public void setMid_score(float mid_score) {
        this.mid_score = mid_score;
    }

    public float getEnd_con() {
        return end_con;
    }

    public void setEnd_con(float end_con) {
        this.end_con = end_con;
    }

    public float getEnd_score() {
        return end_score;
    }

    public void setEnd_score(float end_score) {
        this.end_score = end_score;
    }

    public float getReport_score() {
        return report_score;
    }

    public void setReport_score(float report_score) {
        this.report_score = report_score;
    }

    public float getWeekly_score() {
        return weekly_score;
    }

    public void setWeekly_score(float weekly_score) {
        this.weekly_score = weekly_score;
    }

    public float getAdd_score() {
        return add_score;
    }

    public void setAdd_score(float add_score) {
        this.add_score = add_score;
    }

    public float getRed_score() {
        return red_score;
    }

    public void setRed_score(float red_score) {
        this.red_score = red_score;
    }

    public float getAll_score() {
        return all_score;
    }

    public void setAll_score(float all_score) {
        this.all_score = all_score;
    }

    public float getGit_score() {
        return git_score;
    }

    public void setGit_score(float git_score) {
        this.git_score = git_score;
    }

    public String getFinal_score() {
        return final_score;
    }

    public void setFinal_score(String final_score) {
        this.final_score = final_score;
    }
}
