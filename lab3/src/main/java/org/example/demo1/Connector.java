package org.example.demo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Connector {

    private SessionFactory sessionFactory;

    public Connector() {
        // Inicjalizacja fabryki sesji Hibernate z pliku konfiguracyjnego
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml") // Plik konfiguracyjny Hibernate
                .addAnnotatedClass(EntityPerson.class) // Dodanie encji
                .buildSessionFactory();
    }

}