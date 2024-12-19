package com.karol.zadanie5.controller;

import com.karol.zadanie5.model.Teacher;
import com.karol.zadanie5.model.TeacherCondition;
import com.karol.zadanie5.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeacherController.class)
class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TeacherService teacherService;

    private Teacher teacher1;
    private Teacher teacher2;

    @BeforeEach
    void setUp() {
        teacher1 = new Teacher("John", "Doe", TeacherCondition.OBECNY, 1980, 50000.0);
        teacher1.setId(1L);
        teacher2 = new Teacher("Jane", "Smith", TeacherCondition.NIEOBECNY, 1990, 40000.0);
        teacher2.setId(2L);
    }

    @Test
    void shouldAddTeacher() throws Exception {
        Mockito.when(teacherService.addTeacher(any(Teacher.class), eq(1L))).thenReturn(teacher1);

        String teacherJson = """
                {
                    "name": "John",
                    "surname": "Doe",
                    "teacherCondition": "OBECNY",
                    "yearOfBirth": 1980,
                    "salary": 50000
                }
                """;

        mockMvc.perform(post("/api/teacher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teacherJson)
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.surname").value("Doe"))
                .andExpect(jsonPath("$.teacherCondition").value("ACTIVE"))
                .andExpect(jsonPath("$.yearOfBirth").value(1980))
                .andExpect(jsonPath("$.salary").value(50000.0));
    }

    @Test
    void shouldGetAllTeachers() throws Exception {
        List<Teacher> teachers = Arrays.asList(teacher1, teacher2);
        Mockito.when(teacherService.getTeachers()).thenReturn(teachers);

        mockMvc.perform(get("/api/teacher"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[0].teacherCondition").value("OBECNY"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Jane"))
                .andExpect(jsonPath("$[1].teacherCondition").value("NIEOBECNY"));
    }

    @Test
    void shouldDeleteTeacher() throws Exception {
        Mockito.doNothing().when(teacherService).deleteTeacher(1L);

        mockMvc.perform(delete("/api/teacher/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetTeachersAsCSV() throws Exception {
        List<Teacher> teachers = Arrays.asList(teacher1, teacher2);
        Mockito.when(teacherService.getTeachers()).thenReturn(teachers);

        mockMvc.perform(get("/api/teacher/csv"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "text/csv"))
                .andExpect(header().string("Content-Disposition", "attachment; filename=teachers.csv"))
                .andExpect(content().string("ID,NAME,SURNAME\n1,John,Doe\n2,Jane,Smith\n"));
    }
}
