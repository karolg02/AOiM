package org.example.demo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Connector {
    private static Connector instance;
    private static SessionFactory sessionFactory;

    Connector() {
        try {
            // Inicjalizacja fabryki sesji Hibernate z pliku konfiguracyjnego
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml") // Plik konfiguracyjny Hibernate
                    .addAnnotatedClass(Teacher.class)
                    .addAnnotatedClass(ClassTeacher.class) // Dodanie encji
                    .addAnnotatedClass(ClassContainer.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connector getInstance() {
        if (instance == null) {
            synchronized (Connector.class) {
                if (instance == null) {
                    instance = new Connector();
                }
            }
        }
        return instance;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void shutdown() {
        sessionFactory.close();
    }
}
