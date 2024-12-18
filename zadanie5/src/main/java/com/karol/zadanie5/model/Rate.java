package com.karol.zadanie5.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int value;

    private LocalDate date;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "class_teacher_id", nullable = false)
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

    public void setGroup(ClassTeacher classTeacher) {
        this.classTeacher = classTeacher;
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
}
