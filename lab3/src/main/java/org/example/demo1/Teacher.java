package org.example.demo1;

import jakarta.persistence.*;

@Entity
@Table(name = "Teacher")
public class Teacher implements Comparable<Teacher> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Określamy, że id będzie auto-generowane
    @Column(name = "id")  // Nazwa kolumny w tabeli
    private Long id;

    private String imie;  // Pole 'imie' zamiast 'name'

    private String nazwisko;

    private TeacherCondition teacherCondition;

    private Integer rokUrodzenia;

    private double wynagrodzenie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_teacher_id")
    private ClassTeacher classTeacher;

    public Teacher(String imie, String nazwisko, TeacherCondition stanNauczyciela, Integer rokUrodzenia, double wynagrodzenie) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.teacherCondition = stanNauczyciela;
        this.rokUrodzenia = rokUrodzenia;
        this.wynagrodzenie = wynagrodzenie;
    }

    public Teacher() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public int compareTo(Teacher other) {
        return this.nazwisko.compareTo(other.nazwisko);
    }

    public ClassTeacher getClassTeacher() {
        return classTeacher;
    }
    public void setClassTeacher(ClassTeacher classTeacher) {
        this.classTeacher = classTeacher;
    }
}
