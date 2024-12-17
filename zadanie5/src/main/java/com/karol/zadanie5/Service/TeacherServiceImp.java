package com.karol.zadanie5.Service;

import com.karol.zadanie5.Exceptions.Exceptions;
import com.karol.zadanie5.Repository.TeacherRepository;
import com.karol.zadanie5.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImp implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImp(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(Exceptions.ResourceNotFoundException::new);
        teacherRepository.delete(teacher);
    }
}
