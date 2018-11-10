package com.learn.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clas_id")
    private Clas clas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "team")
    private List<PerGrade> perGrades;

    private String name;

    private float open_score;

    private float mid_score;

    private float end_score;

    private float report_score;

    private float weekly_score;

    private float git_score;

    private int uploadweek;

    private int uploadopen;

    private int uploadmid;

    private int uploadend;

    private int uploadopencon;

    private int uploadmidcon;

    private int uploadendcon;

    public int getUploadopencon() {
        return uploadopencon;
    }

    public void setUploadopencon(int uploadopencon) {
        this.uploadopencon = uploadopencon;
    }

    public int getUploadmidcon() {
        return uploadmidcon;
    }

    public void setUploadmidcon(int uploadmidcon) {
        this.uploadmidcon = uploadmidcon;
    }

    public int getUploadendcon() {
        return uploadendcon;
    }

    public void setUploadendcon(int uploadendcon) {
        this.uploadendcon = uploadendcon;
    }

    public int getUploadweek() {
        return uploadweek;
    }

    public void setUploadweek(int uploadweek) {
        this.uploadweek = uploadweek;
    }

    public int getUploadopen() {
        return uploadopen;
    }

    public void setUploadopen(int uploadopen) {
        this.uploadopen = uploadopen;
    }

    public int getUploadmid() {
        return uploadmid;
    }

    public void setUploadmid(int uploadmid) {
        this.uploadmid = uploadmid;
    }

    public int getUploadend() {
        return uploadend;
    }

    public void setUploadend(int uploadend) {
        this.uploadend = uploadend;
    }

    public List<PerGrade> getPerGrades() {
        return perGrades;
    }

    public void setPerGrades(List<PerGrade> perGrades) {
        this.perGrades = perGrades;
    }

    public float getGit_score() {
        return git_score;
    }

    public void setGit_score(float git_score) {
        this.git_score = git_score;
    }

    public Clas getClas() {
        return clas;
    }

    public void setClas(Clas clas) {
        this.clas = clas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getOpen_score() {
        return open_score;
    }

    public void setOpen_score(float open_score) {
        this.open_score = open_score;
    }

    public float getMid_score() {
        return mid_score;
    }

    public void setMid_score(float mid_score) {
        this.mid_score = mid_score;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
