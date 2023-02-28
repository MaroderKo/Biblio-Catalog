package com.smart.catalog.Service;

import com.smart.catalog.Domain.Student;
import com.smart.catalog.Repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends AbstractService<Student, StudentRepository>{
    public StudentService(StudentRepository repository) {
        super(repository);
    }
}
