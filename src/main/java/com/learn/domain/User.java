package com.learn.domain;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String uid;

    private String psword;

    private int identity;

    @OneToOne(fetch = FetchType.EAGER)
    private Team team;

    @OneToOne(fetch = FetchType.EAGER)
    private Teacher teacher;

    public User(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPsword() {
        return psword;
    }

    public void setPsword(String psword) {
        this.psword = psword;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
