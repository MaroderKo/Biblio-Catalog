package com.smart.catalog.repository;

import com.smart.catalog.domain.SchoolClass;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolClassRepository extends AbstractRepository<SchoolClass> {
    Optional<SchoolClass> findSchoolClassByNameEquals(String name);
}
