package com.karol.zadanie5.Service;

import com.karol.zadanie5.model.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher addTeacher(Teacher teacher, Long id);

    List<Teacher> getTeachers();

    void deleteTeacher(Long id);
}
