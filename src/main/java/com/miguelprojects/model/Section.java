package com.miguelprojects.model;

import jakarta.persistence.*;
import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType;

@Entity
@Table(name = "section")

public class Section {

    @Id
    private String id;

    @Column(name = "course_code")
    private String course_code;

    @Column(name = "room_num")
    private int room_num;

    @Column(name = "instructor")
    private String instructor;


    public Section() {}

    public Section(String id, String course_code, int room_num, String instructor) {
        this.id = id;
        this.course_code = course_code;
        this.room_num = room_num;
        this.instructor = instructor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public int getRoom_num() {
        return room_num;
    }

    public void setRoom_num(int room_num) {
        this.room_num = room_num;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", course_code='" + course_code + '\'' +
                ", room_num=" + room_num +
                ", instructor='" + instructor + '\'' +
                '}';
    }
}
