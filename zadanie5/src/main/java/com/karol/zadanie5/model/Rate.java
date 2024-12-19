package com.karol.zadanie5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Value is required")
    @Positive(message = "Value must be a positive integer")
    private int value;

    @NotNull
    private LocalDate date;

    @NotBlank(message = "Comment is required")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "class_teacher_id", nullable = false)
    @JsonIgnore
    private ClassTeacher classTeacher;

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value < 0 || value > 6) {
            throw new IllegalArgumentException("Wartość oceny musi być w zakresie 0-6");
        }
        this.value = value;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public ClassTeacher getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(ClassTeacher classTeacher) {
        this.classTeacher = classTeacher;
    }
}
