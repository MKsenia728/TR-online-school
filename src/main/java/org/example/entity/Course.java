package org.example.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
@Setter
@Getter

@NoArgsConstructor

public class Course {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "type_course")
    @Enumerated(EnumType.STRING)
    private TypeCourse typeCourse;
    @Column(name = "duration")
    private int duration;
    @ManyToMany()
    @JoinTable(name="students_courses",
    joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name="student_id"))
    private Set<Student> students = new HashSet<>();

    public Course(String courseName, TypeCourse typeCourse, int duration) {
        this.courseName = courseName;
        this.typeCourse = typeCourse;
        this.duration = duration;
    }

    public void addStudent(Student student) {
        if (student == null) {
            throw new NullPointerException("Student is empty");
        }
        students.add(student);
    }


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", typeCourse=" + typeCourse +
                ", duration=" + duration +
                '}';
    }
}
