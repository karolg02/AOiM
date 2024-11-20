package org.example.demo1;
public class Teacher implements Comparable<Teacher> {
    String imie;
    String nazwisko;
    TeacherCondition StanNauczyciela;
    Integer rokUrodzenia;
    double wynagrodzenie;

    public Teacher(String imie, String nazwisko, TeacherCondition stanNauczyciela, Integer rokUrodzenia, double wynagrodzenie) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        StanNauczyciela = stanNauczyciela;
        this.rokUrodzenia = rokUrodzenia;
        this.wynagrodzenie = wynagrodzenie;
    }

    @Override
    public int compareTo(Teacher other) {
        return this.nazwisko.compareTo(other.nazwisko);
    }
}
