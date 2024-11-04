//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Teacher teacher = new Teacher("Karol","Glanowski", TeacherCondition.OBECNY,2002,7000);
        Teacher teacher2 = new Teacher("Tomasz","Gacek", TeacherCondition.OBECNY,2002,7000);
        Teacher teacher3 = new Teacher("Mateusz","Jasek", TeacherCondition.OBECNY,2002,7000);
        //teacher.Printing();
        if(teacher.compareTo(teacher2)==0){
            System.out.println("Nazwiska nauczycieli sa takie same!");
        }else{
            System.out.println("Nazwiska nauczycieli nie sa takie same!");
        }
        ClassTeacher classTeacher = new ClassTeacher();
        classTeacher.addToList(teacher);
        classTeacher.addToList(teacher2);
        classTeacher.addToList(teacher3);
        // - dodawanie nauczyciela do grupy
        //classTeacher.addToGroup(teacher);
        //System.out.println(classTeacher.teachers);
        //classTeacher.addToGroup(teacher);
        //System.out.println(classTeacher.teachers);

        //classTeacher.addSalary(teacher, 8000);
        //teacher.Printing();

        // - usuwanie nauczyciela
        //classTeacher.removeTeacher(teacher);
        //System.out.println(classTeacher.teachers);

        // - zmiana stanu nauczyciela
        //classTeacher.changeCondition(teacher,TeacherCondition.DELEGACJA);
        //System.out.println(teacher);

        // - szukanie po nazwisku
        //classTeacher.search("Glanowski");

        // - szuaknie po fragmencie nazwiska
        //classTeacher.searchPartial("Glano");

        // - zwraca ilosc nauczycieli z podanym stanem
        //classTeacher.countByCondition(TeacherCondition.OBECNY);

        // - wypisuje wszystkich nauczycieli na liscie
        //classTeacher.summary();
    }
}