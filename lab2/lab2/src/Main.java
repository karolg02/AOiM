//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ClassContainer classContainer = new ClassContainer();

        //dodawanie grup
        classContainer.addClass("Maruderzy",5);
        classContainer.addClass("Muszkieterzy",5);

        Teacher teacher = new Teacher("Karol","Glanowski", TeacherCondition.OBECNY,2002,7003);
        Teacher teacher2 = new Teacher("Tomasz","Gacek", TeacherCondition.OBECNY,2002,7000);
        Teacher teacher3 = new Teacher("Mateusz","Jasek", TeacherCondition.OBECNY,2002,7002);
        // - porownywanie po nazwisku
//        if(teacher.compareTo(teacher2)==0){
//            System.out.println("Nazwiska nauczycieli sa takie same!");
//        }else{
//            System.out.println("Nazwiska nauczycieli nie sa takie same!");
//        }
        ClassTeacher classTeacher = classContainer.teacherGroups.get("Maruderzy");

        // - dodawanie do grupy i listy
        classTeacher.addTeacher(teacher);
        classTeacher.addTeacher(teacher2);
        classTeacher.addTeacher(teacher3);
        //classTeacher.addTeacher(teacher2);

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

        // - sortowanie po imieniu
        //classTeacher.sortByName();

        // - sortowanie po wynagrodzeniu
        //classTeacher.sortBySalary();

        // - zwraca pracownika z najwiekszym wynagrodzeniem
        //classTeacher.max();

        // 5b
        //classContainer.removeClass("d");

        // 5c
        //System.out.println("Puste grupy: " + classContainer.findEmpty());

        // 5d
        //classContainer.summary();

    }
}