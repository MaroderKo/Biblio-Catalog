package com.smart.catalog.repository;

import com.smart.catalog.domain.ClassOrder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClassOrderRepository extends AbstractRepository<ClassOrder> {
    List<ClassOrder> getClassOrdersBySchoolClass_Id(int id);

    List<ClassOrder> findDistinctByBook_Id(int id);
}
