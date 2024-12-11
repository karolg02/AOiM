package org.example.demo1;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClassContainer {
    public void addClass(String groupName, int capacity) {
        try (Session session = Connector.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            ClassTeacher existingGroup = session.createQuery(
                            "FROM ClassTeacher WHERE groupName = :groupName", ClassTeacher.class)
                    .setParameter("groupName", groupName)
                    .uniqueResult();

            if (existingGroup != null) {
                System.out.println("Grupa o takiej nazwie już istnieje!");
            } else {
                ClassTeacher newGroup = new ClassTeacher(groupName, capacity);
                session.persist(newGroup);
                transaction.commit();
                System.out.println("Dodano nową grupę: " + groupName);
            }
        }
    }

    public List<ClassTeacher> getClassTeachers() {
        try (Session session = Connector.getSessionFactory().openSession()) {
            return session.createQuery("FROM ClassTeacher", ClassTeacher.class).list();
        }
    }

    public void deleteClass(String groupName) {
        try (Session session = Connector.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            ClassTeacher group = session.createQuery(
                            "FROM ClassTeacher WHERE groupName = :groupName", ClassTeacher.class)
                    .setParameter("groupName", groupName)
                    .uniqueResult();

            if (group != null) {
                session.remove(group);
                transaction.commit();
                System.out.println("Usunięto grupę: " + groupName);
            }
        }
    }
}
