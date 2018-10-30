package com.learn.domain;


import javax.persistence.*;
import java.util.List;

@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grade")
    private List<Clas> clas;

    public Grade(){

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

    public List<Clas> getClas() {
        return clas;
    }

    public void setClas(List<Clas> clas) {
        this.clas = clas;
    }
}
