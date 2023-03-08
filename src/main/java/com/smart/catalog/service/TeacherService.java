package com.smart.catalog.service;

import com.smart.catalog.domain.Teacher;
import com.smart.catalog.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private final TeacherRepository repository;

    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    public List<Teacher> getAllSortedByName()
    {
        return repository.findAll().stream().sorted(Comparator.comparing(Teacher::getPib)).collect(Collectors.toUnmodifiableList());
    }

    public List<Teacher> getAll()
    {
        return repository.findAll();
    }

    public void save(Teacher teacher) {
        repository.save(teacher);
    }

    public Teacher findByName(String teacher) {
        return repository.findByPib(teacher);
    }


}
