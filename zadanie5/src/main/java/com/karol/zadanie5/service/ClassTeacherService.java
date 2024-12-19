package com.karol.zadanie5.service;

import com.karol.zadanie5.model.ClassTeacher;
import com.karol.zadanie5.model.Teacher;

import java.util.List;

public interface ClassTeacherService {
    List<ClassTeacher> getClassTeachers();

    ClassTeacher addClassTeachers(ClassTeacher classTeacher);

    void deleteClassTeacher(Long id);

    List<Teacher> getAllTeachersInClass(Long id);
}
