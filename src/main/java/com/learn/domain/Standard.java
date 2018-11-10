package com.learn.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Standard {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "standard")
    private List<CourseStandard> courseStandards;

    public Standard(){

    }

    public List<CourseStandard> getCourseStandards() {
        return courseStandards;
    }

    public void setCourseStandards(List<CourseStandard> courseStandards) {
        this.courseStandards = courseStandards;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
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
