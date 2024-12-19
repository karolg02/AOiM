package com.karol.zadanie5.service;

import com.karol.zadanie5.exceptions.Exceptions;
import com.karol.zadanie5.repository.ClassTeacherRepository;
import com.karol.zadanie5.repository.TeacherRepository;
import com.karol.zadanie5.model.ClassTeacher;
import com.karol.zadanie5.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImp implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final ClassTeacherRepository classTeacherRepository;

    public TeacherServiceImp(TeacherRepository teacherRepository, ClassTeacherRepository classTeacherRepository) {
        this.teacherRepository = teacherRepository;
        this.classTeacherRepository = classTeacherRepository;
    }

    @Override
    public Teacher addTeacher(Teacher teacher, Long classTeacherId) {
        ClassTeacher classTeacher = classTeacherRepository.findById(Math.toIntExact(classTeacherId))
                .orElseThrow(Exceptions.ResourceNotFoundException::new);

        if(classTeacher.getFilledPercentage()+ (double) 1 /classTeacher.getCapacity()>=100){
            throw new Exceptions.CannotAddResourceException();
        }

        teacher.setClassTeacher(classTeacher);

        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(Exceptions.ResourceNotFoundException::new);
        teacher.setClassTeacher(null);
        teacherRepository.delete(teacher);
    }
}
