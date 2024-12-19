package com.karol.zadanie5.controller;

import com.karol.zadanie5.model.ClassTeacher;
import com.karol.zadanie5.model.Teacher;
import com.karol.zadanie5.model.TeacherCondition;
import com.karol.zadanie5.service.ClassTeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClassTeacherController.class)
class ClassTeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClassTeacherService classTeacherService;

    private ClassTeacher classTeacher;
    private Teacher teacher1;
    private Teacher teacher2;

    @BeforeEach
    void setup() {
        teacher1 = new Teacher("John", "Doe", TeacherCondition.OBECNY, 1980, 50000.0);
        teacher1.setId(1L);
        teacher2 = new Teacher( "Jane", "Smith", TeacherCondition.NIEOBECNY, 1985, 48000.0);
        teacher2.setId(2L);

        classTeacher = new ClassTeacher();
        classTeacher.setId(1L);
        classTeacher.setGroupName("Class 1A");
        teacher1.setClassTeacher(classTeacher);
        teacher2.setClassTeacher(classTeacher);
    }

    @Test
    void shouldGetAllClassTeachers() throws Exception {
        List<ClassTeacher> classTeachers = List.of(classTeacher);
        Mockito.when(classTeacherService.getClassTeachers()).thenReturn(classTeachers);

        mockMvc.perform(get("/api/classteacher"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].groupName").value("Class 1A"));
    }

    @Test
    void shouldCreateClassTeacher() throws Exception {
        Mockito.when(classTeacherService.addClassTeachers(any(ClassTeacher.class))).thenReturn(classTeacher);

        String classTeacherJson = """
            {
                "groupName": "Class 1A",
                "capacity": 5
            }
            """;

        mockMvc.perform(post("/api/classteacher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(classTeacherJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.groupName").value("Class 1A"));
    }


    @Test
    void shouldDeleteClassTeacher() throws Exception {
        Mockito.doNothing().when(classTeacherService).deleteClassTeacher(1L);

        mockMvc.perform(delete("/api/classteacher/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetClassTeachers() throws Exception {
        List<Teacher> teachers = Arrays.asList(teacher1, teacher2);
        Mockito.when(classTeacherService.getAllTeachersInClass(1L)).thenReturn(teachers);

        mockMvc.perform(get("/api/classteacher/1/teachers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[0].surname").value("Doe"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Jane"))
                .andExpect(jsonPath("$[1].surname").value("Smith"));
    }
}
