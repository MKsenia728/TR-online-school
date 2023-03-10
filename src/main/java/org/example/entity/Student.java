package org.example.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "students")
@Setter
@Getter
@NoArgsConstructor

public class Student {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String familyName;
    @Column(name = "date_started")
    private Timestamp dateStarted;
    private String groupNr;
    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<>();

    public Student(String firstName, String familyName, Timestamp dateStarted, String groupNr) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.dateStarted = dateStarted;
        this.groupNr = groupNr;
    }

    public void addCourse(Course course) {
        if (course == null) {
            throw new NullPointerException("Course should not be empty");
        } else {
            courses.add(course);
            course.addStudent(this);
        }
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", dateStarted=" + dateStarted +
                ", groupNr='" + groupNr + '\'' + courses.toString() +
//                ", courses=" + courses +
                '}';
    }
}

