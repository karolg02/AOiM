package com.karol.zadanie5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "class_teacher")
public class ClassTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "groupName is required")
    private String groupName;

    @NotNull(message = "Capacity is required")
    @Positive(message = "Capacity must be a positive integer")
    private int capacity;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "class_teacher_id")
    @JsonIgnore
    private List<Teacher> teacherArrayList = new ArrayList<>();

    public ClassTeacher() {}

    public Long getId() {
        return id;
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
        return teacherArrayList.size() * 100.0 / capacity;
    }

    public List<Teacher> getTeacherArrayList() {
        return teacherArrayList;
    }
}
