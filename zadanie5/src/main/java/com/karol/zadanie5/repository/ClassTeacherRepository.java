package com.karol.zadanie5.repository;

import com.karol.zadanie5.model.ClassTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassTeacherRepository extends JpaRepository<ClassTeacher, Integer> {
}
