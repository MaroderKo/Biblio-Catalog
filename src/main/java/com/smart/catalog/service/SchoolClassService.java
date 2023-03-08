package com.smart.catalog.service;

import com.smart.catalog.domain.SchoolClass;
import com.smart.catalog.repository.SchoolClassRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolClassService {

    private final SchoolClassRepository repository;

    public SchoolClassService(SchoolClassRepository repository) {
        this.repository = repository;
    }

    public SchoolClass getByName(String name)
    {
        return repository.findSchoolClassByNameEquals(name).orElse(null);
    }

    public List<String> getClassNames() {
        return repository.findAll().stream().map(SchoolClass::getName).collect(Collectors.toUnmodifiableList());
    }

    public List<SchoolClass> getAllSortedByName() {
        return repository.findAll().stream().sorted(Comparator.comparing(SchoolClass::getName)).collect(Collectors.toUnmodifiableList());
    }

    public SchoolClass save(SchoolClass schoolClass)
    {
        return repository.save(schoolClass);
    }
}
