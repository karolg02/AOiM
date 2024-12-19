package com.karol.zadanie5.repository;

import com.karol.zadanie5.model.ClassTeacher;
import com.karol.zadanie5.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer> {
    List<Rate> findAllByClassTeacher(ClassTeacher classTeacher);
}
