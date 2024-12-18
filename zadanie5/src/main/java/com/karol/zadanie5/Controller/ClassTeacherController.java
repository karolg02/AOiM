package com.karol.zadanie5.Controller;

import com.karol.zadanie5.Service.ClassTeacherService;
import com.karol.zadanie5.model.ClassTeacher;
import com.karol.zadanie5.model.Teacher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classteacher")
public class ClassTeacherController {
    private final ClassTeacherService classTeacherService;

    public ClassTeacherController(ClassTeacherService classTeacherService) {
        this.classTeacherService = classTeacherService;
    }

    @GetMapping
    public List<ClassTeacher> getAllClassTeachers() {
        return classTeacherService.getClassTeachers();
    }

    @PostMapping
    public ClassTeacher createClassTeacher(@RequestBody ClassTeacher classTeacher) {
        return classTeacherService.addClassTeachers(classTeacher);
    }

    @DeleteMapping("/{id}")
    public void deleteClassTeacher(@PathVariable Long id) {
        classTeacherService.deleteClassTeacher(id);
    }

    @GetMapping("/{id}/teachers")
    public List<Teacher> getClassTeachers(@PathVariable Long id) {
        return classTeacherService.getAllTeachersInClass(id);
    }
}
