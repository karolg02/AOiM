package com.karol.zadanie5.Service;

import com.karol.zadanie5.Exceptions.Exceptions;
import com.karol.zadanie5.Repository.ClassTeacherRepository;
import com.karol.zadanie5.Repository.RateRepository;
import com.karol.zadanie5.model.ClassTeacher;
import com.karol.zadanie5.model.Rate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RateServiceImp implements RateService {

    private final RateRepository rateRepository;
    private final ClassTeacherRepository classTeacherRepository;
    public RateServiceImp(RateRepository rateRepository, ClassTeacherRepository classTeacherRepository) {
        this.rateRepository = rateRepository;
        this.classTeacherRepository = classTeacherRepository;
    }

    @Override
    public List<Rate> getAllRates(Long id) {
        ClassTeacher classTeacher = classTeacherRepository.findById(Math.toIntExact(id))
                .orElseThrow(Exceptions.ResourceNotFoundException::new);

        return rateRepository.findAllByClassTeacher(classTeacher);
    }


    @Override
    public Rate addRate(Rate rate, Long id) {
        ClassTeacher classTeacher = classTeacherRepository.findById(Math.toIntExact(id)).orElseThrow(Exceptions.ResourceNotFoundException::new);
        rate.setGroup(classTeacher);
        rate.setDate(LocalDate.now());
        return rateRepository.save(rate);
    }
}
