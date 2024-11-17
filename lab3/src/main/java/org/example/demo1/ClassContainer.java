package org.example.demo1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClassContainer {
    private Map<String, ClassTeacher> teacherGroups;
    private static ClassContainer instance;

    private ClassContainer() {
        teacherGroups = new HashMap<>(); // Inicjalizacja mapy w konstruktorze
    }

    public static ClassContainer getInstance() {
        if (instance == null) {
            instance = new ClassContainer();

            instance.addClass("Maruderzy", 5);
            instance.addClass("Muszkieterzy", 5);

            Teacher teacher1 = new Teacher("Karol", "Glanowski", TeacherCondition.OBECNY, 2002, 7003);
            Teacher teacher2 = new Teacher("Tomasz", "Gacek", TeacherCondition.OBECNY, 2002, 7000);
            Teacher teacher3 = new Teacher("Mateusz", "Jasek", TeacherCondition.OBECNY, 2002, 7002);

            ClassTeacher classTeacher = instance.teacherGroups.get("Maruderzy");
            if (classTeacher != null) {
                classTeacher.addTeacher(teacher1);
                classTeacher.addTeacher(teacher2);
                classTeacher.addTeacher(teacher3);
            }
        }
        return instance;
    }


    public List<ClassTeacher> getClassTeachers() {
        return teacherGroups.values().stream().collect(Collectors.toList());
    }

    public void addClass(String groupName, double Capacity){
        if(teacherGroups.containsKey(groupName)){
            System.out.println("Grupa o takiej nazwie juz istnieje!");
        }else{
            teacherGroups.put(groupName, new ClassTeacher(groupName,10));
            System.out.println("Dodano nowa grupe: " + groupName);
        }
    }

    public void removeClass(String groupName){
        if(teacherGroups.containsKey(groupName)){
            System.out.println("Usunieto grupe: " + groupName);
            teacherGroups.remove(groupName);
        }else{
            System.out.println("Nie ma takiej grupy!");
        }
    }

    public List<String> findEmpty(){
        List<String> empty = new ArrayList<>();
        for(Map.Entry<String, ClassTeacher> entry : teacherGroups.entrySet()){
            if(entry.getValue().teachers.isEmpty()){
                empty.add(entry.getKey());
            }
        }
        return empty;
    }

    public void summary(){
        for(Map.Entry<String, ClassTeacher> entry : teacherGroups.entrySet()){
            ClassTeacher teacher = entry.getValue();
            int size = teacher.teachers.size();
            double percent = ((double) size/ teacher.capacity)*100;
            System.out.println(teacher.groupName + ": procentowe zapełnienie: " + percent + "%");
        }
    }

    public List<ClassTeacher> searchGroupsByName(String searchText) {
        return teacherGroups.values().stream()
                .filter(classTeacher -> classTeacher.groupName.toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

}
