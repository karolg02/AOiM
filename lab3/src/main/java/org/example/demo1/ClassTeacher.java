package org.example.demo1;

import jakarta.persistence.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

@Entity
@Table(name = "class_teacher")
public class ClassTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name", unique = true, nullable = false)
    private String groupName;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    public ClassTeacher() {
    }

    public ClassTeacher(String groupName, int capacity) {
        this.groupName = groupName;
        this.capacity = capacity;
    }

    // Gettery i settery
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getFilledPercentage() {
        return capacity * 1.0 / capacity;
    }

    public ObservableValue<String> nameProperty() {
        return new SimpleStringProperty(groupName);
    }

    public Teacher getTeachers() {
        return getTeachers();
    }
}
