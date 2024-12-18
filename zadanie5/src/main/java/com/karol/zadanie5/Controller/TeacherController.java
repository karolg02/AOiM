package com.karol.zadanie5.Controller;

import com.karol.zadanie5.Service.TeacherService;
import com.karol.zadanie5.model.Teacher;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public Teacher addTeacher(@Valid @RequestBody Teacher teacher, @RequestParam Long id) {
        return teacherService.addTeacher(teacher, id);
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getTeachers();
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }

    @GetMapping("/csv")
    public void getAllTeachersCSV(HttpServletResponse response) {
        List<Teacher> teachers = teacherService.getTeachers();
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=teachers.csv");

        try(ServletOutputStream outputStream = response.getOutputStream()){
            outputStream.println("ID,NAME,SURNAME");

            for (Teacher teacher : teachers) {
                outputStream.println(teacher.getId() + "," + teacher.getName() + "," + teacher.getSurname());
            }

            outputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
