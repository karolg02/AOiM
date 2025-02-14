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
    public void Printing(){
        System.out.println("Imie: " + imie);
        System.out.println("Nazwisko: " + nazwisko);
        System.out.println("Stan nauczyciela: " + StanNauczyciela);
        System.out.println("Rok urodzenia: " + rokUrodzenia);
        System.out.println("Wynagrodzenie: " + wynagrodzenie);
    }

    @Override
    public int compareTo(Teacher other) {
        return this.nazwisko.compareTo(other.nazwisko);
    }
}
