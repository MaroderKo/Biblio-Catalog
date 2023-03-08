package com.smart.catalog.repository;

import com.smart.catalog.domain.StudentOrder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentOrderRepository extends AbstractRepository<StudentOrder>{

    List<StudentOrder> getStudentOrdersByStudent_Id(int id);

    List<StudentOrder> getStudentOrdersByBook_Id(int id);

    int countDistinctByBook_Id(int id);
}
