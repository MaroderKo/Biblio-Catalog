package com.smart.catalog.service;

import com.smart.catalog.domain.Book;
import com.smart.catalog.domain.Student;
import com.smart.catalog.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);
    StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student save(Student student) {
        if (student.getId() == 0)
        {
            LOG.info("Збережено нового учня -> "+student.getPib());
        }
        else
        {
            LOG.info("Змінено учня -> "+student.getPib());
            LOG.info("Попередні дані:"+repository.findById(student.getId()));
        }
        Student save = repository.save(student);
        LOG.info("Нові дані -> "+save);
        return save;
    }

    public Student getByName(String student) {
        return repository.findByPib(student);
    }
}
