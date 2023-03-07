package com.smart.catalog.service;

import com.smart.catalog.domain.SchoolClass;
import com.smart.catalog.repository.SchoolClassRepository;
import org.springframework.stereotype.Service;

@Service
public class SchoolClassService extends AbstractService<SchoolClass, SchoolClassRepository> {
    public SchoolClass getByName(String name)
    {
        return repository.findSchoolClassByNameEquals(name).orElse(null);
    }
    public SchoolClassService(SchoolClassRepository repository) {
        super(repository);
    }
}
