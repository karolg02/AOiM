package org.example.demo1;

import org.hibernate.Session;

import java.util.List;

public class ClassContainer {

    public List<ClassTeacher> getClassTeachers() {
        try (Session session = Connector.getSessionFactory().openSession()) {
            return session.createQuery("FROM ClassTeacher", ClassTeacher.class).list();
        }
    }
}
