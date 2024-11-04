import java.util.ArrayList;
import java.util.Comparator;

public class ClassTeacher implements Comparable<ClassTeacher> {
    @Override
    public int compareTo(ClassTeacher o) {
        
    }

    public ArrayList<Teacher> teachers = new ArrayList<>(10);
    public ArrayList<Teacher> group = new ArrayList<>(5);

    void addToList(Teacher teacher) {
        teachers.add(teacher);
    }

    void addToGroup(Teacher teacher) {
        group.add(teacher);
    }

    //b
    void addSalary(Teacher teacher, double wynagrodzenie){
        teacher.wynagrodzenie = wynagrodzenie;
    }
    //c
    void removeTeacher(Teacher teacher){
        teachers.remove(teacher);
        group.remove(teacher);
    }
    //d
    void changeCondition(Teacher teacher, TeacherCondition condition){
        teacher.StanNauczyciela = condition;
    }

    //e
    void search(String surename){
        for (Teacher teacher : teachers) {
            if (teacher.nazwisko.equals(surename)) {
                teacher.Printing();
            }
        }
    }

    //f
    void searchPartial(String text){
        for (Teacher teacher : teachers) {
            if (teacher.nazwisko.contains(text)) {
                teacher.Printing();
            }
        }
    }

    //g
    void countByCondition(TeacherCondition condition){
        int i =0;
        for(Teacher teacher : teachers) {
            if(teacher.StanNauczyciela == condition){
                i++;
            }
        }
        System.out.println("Ilość nauczycieli o  tym stanie " + i);
    }

    //h
    void summary(){
        for(Teacher teacher : teachers) {
            teacher.Printing();
        }
    }

    //i
    void sortByName(){
        for(Teacher teacher : teachers) {

        }
    }

}
