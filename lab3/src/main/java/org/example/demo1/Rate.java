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

    @JoinColumn(name = "group_id", nullable = false) // Kolumna dla identyfikatora grupy
    private String group; // Grupa, dla której wystawiono ocenę

    @Column(nullable = false)
    private LocalDate date; // Data wystawienia oceny

    @Column(nullable = false, length = 500)
    private String comment; // Komentarz (opcjonalny, ale nie null)

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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
