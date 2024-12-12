package org.example.demo1;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Connector {
    private static SessionFactory sessionFactory;

    Connector() {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Teacher.class)
                    .addAnnotatedClass(ClassTeacher.class)
                    .addAnnotatedClass(ClassContainer.class)
                    .addAnnotatedClass(Rate.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    private static final class InstanceHolder {
        private static final Connector instance = new Connector();
    }

    public static Connector getInstance() {
        return InstanceHolder.instance;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
