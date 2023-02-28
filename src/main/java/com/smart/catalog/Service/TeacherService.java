package com.smart.catalog.Service;

import com.smart.catalog.Domain.Student;
import com.smart.catalog.Domain.Teacher;
import com.smart.catalog.Repository.StudentRepository;
import com.smart.catalog.Repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService extends AbstractService<Teacher, TeacherRepository>{
    public TeacherService(TeacherRepository repository) {
        super(repository);
    }
}
