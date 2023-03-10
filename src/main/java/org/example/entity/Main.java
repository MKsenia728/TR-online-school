package org.example.entity;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) {
        Timestamp dateStart1 = Timestamp.valueOf("2023-03-10 00:00:00");
        Student student1 = new Student("Anna", "Ivanova", dateStart1, "311M");
        Student student2 = new Student("Ivan", "Petrov", dateStart1, "313M");
        Student student3 = new Student("Elena", "Andreeva", dateStart1, "311M");
        Student student4 = new Student("Petr", "Sodirov", dateStart1, "311M");
        Student student5 = new Student("Anna", "Belaj", dateStart1, "312M");

        Course course1 = new Course("English", TypeCourse.LANGUAGE, 300);
        Course course2 = new Course("Java", TypeCourse.IT, 900);
        Course course3 = new Course("Javascript", TypeCourse.IT, 700);

        student1.addCourse(course1);
        student1.addCourse(course2);
        student2.addCourse(course1);
        student2.addCourse(course3);
        student3.addCourse(course1);
        student3.addCourse(course2);
        student4.addCourse(course1);
        student4.addCourse(course2);
        student5.addCourse(course1);
        student5.addCourse(course3);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(student1);
        session.save(student2);
        session.save(student3);
        session.save(student4);
        session.save(student5);
        session.save(course1);
        session.save(course2);
        session.save(course3);
        transaction.commit();
        session.close();
//        getStudent("Anna");
        getCourse("Java", "311M");

    }

    public static void getStudent(String name) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var query = session.createQuery("SELECT s FROM Student s JOIN s.courses WHERE s.firstName=:name", Student.class);
        query.setParameter("name", name);
        var listQ = query.getResultList();
        System.out.println(listQ);

    }

    public static void getCourse(String course, String name) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var query = session.createQuery(
                "SELECT c FROM Course c JOIN c.students s WHERE c.courseName=:course AND s.groupNr=:name", Course.class);

        query
                .setParameter("course", course)
                .setParameter("name", name);
        var listQ = query.getResultList();
//        listQ.forEach(e->{
//            System.out.println(e.getStudents());
//        });
        System.out.println(listQ);

    }
}
