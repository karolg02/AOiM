package org.example.demo1;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

public class ClassTeacher{
    public String groupName;
    public List<Teacher> teachers;
    private final int capacity;

    public ClassTeacher(String groupName, int capacity) {
        this.groupName = groupName;
        this.capacity = capacity;
        this.teachers = new ArrayList<>(capacity);
    }

    public SimpleStringProperty nameProperty(){
        return new SimpleStringProperty(groupName);
    }

    public void addTeacher(Teacher teacher) {
        for (Teacher t : teachers) {
            if (t.imie.equals(teacher.imie) && t.nazwisko.equals(teacher.nazwisko)) {
                return;
            }
        }

        if (teachers.size() >= capacity) {
            return;
        }

        teachers.add(teacher);
    }

    public double getFilledPercentage() {
        return ((double) teachers.size() / capacity) * 100;
    }

    public int getCapacity() {
        return capacity;
    }

}
