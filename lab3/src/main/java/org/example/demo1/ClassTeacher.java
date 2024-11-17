package org.example.demo1;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClassTeacher{
    public String groupName;
    public List<Teacher> teachers;
    public int capacity;

    public ClassTeacher(String groupName, int capacity) {
        this.groupName = groupName;
        this.capacity = capacity;
        this.teachers = new ArrayList<>(capacity);
    }

    public SimpleStringProperty nameProperty(){
        return new SimpleStringProperty(groupName);
    }

    // a
    public void addTeacher(Teacher teacher) {
        for (Teacher t : teachers) {
            if (t.imie.equals(teacher.imie) && t.nazwisko.equals(teacher.nazwisko)) {
                System.out.println("Nauczyciel o imieniu " + teacher.imie + " i nazwisku " + teacher.nazwisko + " już istnieje w grupie!");
                return;
            }
        }

        if (teachers.size() >= capacity) {
            System.out.println("Nie można dodać nauczyciela: przekroczona pojemność grupy.");
            return;
        }

        teachers.add(teacher);
        System.out.println("Dodano nauczyciela: " + teacher.imie + " " + teacher.nazwisko);
    }

    //b
    void addSalary(Teacher teacher, double wynagrodzenie){
        teacher.wynagrodzenie = wynagrodzenie;
    }

    //c
    void removeTeacher(Teacher teacher){
        teachers.remove(teacher);
    }

    //d
    void changeCondition(Teacher teacher, TeacherCondition condition){
        teacher.StanNauczyciela = condition;
    }

    //e
    void search(String surename){
        for (Teacher teacher : teachers) {
            if (teacher.nazwisko.equals(surename)) {
                teacher.Printing();
            }
        }
    }

    //f
    void searchPartial(String text){
        for (Teacher teacher : teachers) {
            if (teacher.nazwisko.contains(text)) {
                teacher.Printing();
            }
        }
    }

    //g
    void countByCondition(TeacherCondition condition){
        int i =0;
        for(Teacher teacher : teachers) {
            if(teacher.StanNauczyciela == condition){
                i++;
            }
        }
        System.out.println("Ilość nauczycieli o  tym stanie " + i);
    }

    //h
    void summary(){
        for(Teacher teacher : teachers) {
            teacher.Printing();
        }
    }

    //i
    void sortByName(){
        teachers.sort(Comparator.comparing(teacher -> teacher.imie));
        System.out.println("Posortowano po nazwisku");
        for(Teacher teacher : teachers) {
            teacher.Printing();
        }
    }
    //j
    void sortBySalary(){
        teachers.sort(new Comparator<Teacher>() {
            @Override
            public int compare(Teacher o1, Teacher o2) {
                return Double.compare(o2.wynagrodzenie, o1.wynagrodzenie);
            }
        });
        System.out.println("Posortowano po wynagrodzeniu, malejąco");
        for(Teacher teacher : teachers) {
            teacher.Printing();
        }
    }
    //k
    void max(){
        Teacher teacher = Collections.max(teachers, Comparator.comparingDouble(e->e.wynagrodzenie));
        System.out.println("Nauczyciel z najwiekszym wynagrodzeniem");
        teacher.Printing();
    }

    public double getFilledPercentage() {
        return ((double) teachers.size() / capacity) * 100;
    }

    public int getCapacity() {
        return capacity;
    }

}
