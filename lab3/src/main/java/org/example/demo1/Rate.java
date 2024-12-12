package org.example.demo1;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Klucz główny

    @Column(nullable = false)
    private int value; // Wartość oceny (0-6)

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private ClassTeacher group;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, length = 500)
    private static String comment; // Komentarz (opcjonalny, ale nie null)

    // Gettery i settery
    public Long getId() {
        return id;
    }

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

    public ClassTeacher getGroup() {
        return group;
    }

    public void setGroup(ClassTeacher group) {
        this.group = group;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
