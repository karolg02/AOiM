package com.karol.zadanie5.Service;

import com.karol.zadanie5.Exceptions.Exceptions;
import com.karol.zadanie5.Repository.ClassTeacherRepository;
import com.karol.zadanie5.Repository.TeacherRepository;
import com.karol.zadanie5.model.ClassTeacher;
import com.karol.zadanie5.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassTeacherServiceImp implements ClassTeacherService {
    private final ClassTeacherRepository classTeacherRepository;
    private final TeacherRepository teacherRepository;

    public ClassTeacherServiceImp(ClassTeacherRepository classTeacherRepository, TeacherRepository teacherRepository) {
        this.classTeacherRepository = classTeacherRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<ClassTeacher> getClassTeachers() {
        return classTeacherRepository.findAll();
    }

    @Override
    public ClassTeacher addClassTeachers(ClassTeacher classTeacher) {
        return classTeacherRepository.save(classTeacher);
    }

    @Override
    public void deleteClassTeacher(Long id) {
        ClassTeacher classTeacher = classTeacherRepository.findById(Math.toIntExact(id)).orElseThrow(Exceptions.ResourceNotFoundException::new);
        classTeacherRepository.deleteById(Math.toIntExact(id));
    }

    @Override
    public List<Teacher> getAllTeachersInClass(Long id) {
        ClassTeacher classTeacher = classTeacherRepository.findById(Math.toIntExact(id)).get();
        return classTeacher.getTeacherArrayList();
    }
}
