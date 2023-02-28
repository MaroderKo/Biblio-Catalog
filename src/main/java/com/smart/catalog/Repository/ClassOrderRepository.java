package com.smart.catalog.Repository;

import com.smart.catalog.Domain.Book;
import com.smart.catalog.Domain.ClassOrder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClassOrderRepository extends AbstractRepository<ClassOrder>{
    List<ClassOrder> getClassOrdersBySchoolClass_Id(int id);
}
