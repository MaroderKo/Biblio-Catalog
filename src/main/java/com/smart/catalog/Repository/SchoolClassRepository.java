package com.smart.catalog.Repository;

import com.smart.catalog.Domain.SchoolClass;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolClassRepository extends AbstractRepository<SchoolClass> {
    Optional<SchoolClass> findSchoolClassByNameEquals(String name);
}
