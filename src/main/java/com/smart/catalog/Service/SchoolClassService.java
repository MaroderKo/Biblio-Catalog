package com.smart.catalog.Service;

import com.smart.catalog.Domain.SchoolClass;
import com.smart.catalog.Repository.AbstractRepository;
import com.smart.catalog.Repository.SchoolClassRepository;
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
