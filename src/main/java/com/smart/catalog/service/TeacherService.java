package com.smart.catalog.service;

import com.smart.catalog.domain.Teacher;
import com.smart.catalog.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService extends AbstractService<Teacher, TeacherRepository>{
    public TeacherService(TeacherRepository repository) {
        super(repository);
    }
}
