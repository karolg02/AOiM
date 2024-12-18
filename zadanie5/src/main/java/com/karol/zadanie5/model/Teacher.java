package com.karol.zadanie5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Surename is required")
    private String surname;

    @NotNull(message = "Condition is required")
    private TeacherCondition teacherCondition;

    @NotNull(message = "Year of birth is required")
    @Positive(message = "Year of birth must be a positive integer")
    private Integer yearOfBirth;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be a positive number")
    private Double salary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_teacher_id")
    private ClassTeacher classTeacher;

    public Teacher(String name, String surename, TeacherCondition teacherCondition, Integer yearOfBirth, double salary) {
        this.surname = surename;
        this.name = name;
        this.teacherCondition = teacherCondition;
        this.yearOfBirth = yearOfBirth;
        this.salary = salary;
    }

    public Teacher() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surename) {
        this.surname = surename;
    }

    public TeacherCondition getTeacherCondition() {
        return teacherCondition;
    }

    public void setTeacherCondition(TeacherCondition teacherCondition) {
        this.teacherCondition = teacherCondition;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public ClassTeacher getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(ClassTeacher classTeacher) {
        this.classTeacher = classTeacher;
    }
}
