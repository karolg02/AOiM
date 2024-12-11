package org.example.demo1;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "classTeacher")
public class ClassTeacher{
    @Id
    private long id;
    private String groupName;
    private List<Teacher> teachers;
    private final int capacity;

    public ClassTeacher(String groupName, int capacity) {
        this.groupName = groupName;
        this.capacity = capacity;
        this.teachers = new ArrayList<>(capacity);
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public SimpleStringProperty nameProperty(){
        return new SimpleStringProperty(groupName);
    }

    public double getFilledPercentage() {
        return ((double) teachers.size() / capacity) * 100;
    }

    public int getCapacity() {
        return capacity;
    }

}
