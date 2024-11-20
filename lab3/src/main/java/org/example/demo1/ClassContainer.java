package org.example.demo1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassContainer {
    public Map<String, ClassTeacher> teacherGroups;
    private static ClassContainer instance;

    private ClassContainer() {
        teacherGroups = new HashMap<>();
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
        return new ArrayList<>(teacherGroups.values());
    }

    public void addClass(String groupName, int Capacity){
        if(teacherGroups.containsKey(groupName)){
            System.out.println("Grupa o takiej nazwie juz istnieje!");
        }else{
            teacherGroups.put(groupName, new ClassTeacher(groupName,Capacity));
            System.out.println("Dodano nowa grupe: " + groupName);
        }
    }
}
