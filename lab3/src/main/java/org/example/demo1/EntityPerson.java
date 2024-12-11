package org.example.demo1;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class EntityPerson {

    @Id
    private int id;

    private String imie;  // Pole 'imie' zamiast 'name'

    private String nazwisko;

    private TeacherCondition teacherCondition;

    private Integer rokUrodzenia;

    private double wynagrodzenie;

    // Konstruktor bezargumentowy (wymagany przez JPA)
    public EntityPerson() {}

    // Konstruktor z argumentami
    public EntityPerson(int id, String imie, String nazwisko, TeacherCondition teacherCondition, Integer rokUrodzenia, double wynagrodzenie) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.teacherCondition = teacherCondition;
        this.rokUrodzenia = rokUrodzenia;
        this.wynagrodzenie = wynagrodzenie;
    }

    // Gettery i Settery

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public TeacherCondition getTeacherCondition() {
        return teacherCondition;
    }

    public void setTeacherCondition(TeacherCondition teacherCondition) {
        this.teacherCondition = teacherCondition;
    }

    public Integer getRokUrodzenia() {
        return rokUrodzenia;
    }

    public void setRokUrodzenia(Integer rokUrodzenia) {
        this.rokUrodzenia = rokUrodzenia;
    }

    public double getWynagrodzenie() {
        return wynagrodzenie;
    }

    public void setWynagrodzenie(double wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }
}
