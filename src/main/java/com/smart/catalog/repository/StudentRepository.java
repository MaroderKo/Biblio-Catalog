package com.smart.catalog.repository;

import com.smart.catalog.domain.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends AbstractRepository<Student>{

    public Student findByPib(String name);

}
