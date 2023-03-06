package com.smart.catalog.service;

import com.smart.catalog.domain.Student;
import com.smart.catalog.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends AbstractService<Student, StudentRepository>{
    public StudentService(StudentRepository repository) {
        super(repository);
    }
}
