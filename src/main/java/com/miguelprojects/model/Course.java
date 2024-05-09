package com.miguelprojects.model;

import jakarta.persistence.*;

@Entity
@Table(name = "course")

public class Course {
    @Id
    private String courseCode;

    @Column(name = "course_name")
    private String courseName;

    public Course() {}

    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    public String getId() {
        return courseCode;
    }

    public void setId(String id) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
