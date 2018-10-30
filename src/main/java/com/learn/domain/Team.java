package com.learn.domain;

import javax.persistence.*;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    private String p_name;

    private float open_score;

    private float mid_score;

    private float end_score;

    private float report_score;

    private float weekly_score;

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

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
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
