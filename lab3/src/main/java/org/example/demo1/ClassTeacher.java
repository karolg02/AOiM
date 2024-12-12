package org.example.demo1;

import jakarta.persistence.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "class_teacher_id")
    private List<Teacher> teacherArrayList = new ArrayList<>();

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
        return capacity * 100.0 / capacity;
    }

    public ObservableValue<String> nameProperty() {
        return new SimpleStringProperty(groupName);
    }
}
