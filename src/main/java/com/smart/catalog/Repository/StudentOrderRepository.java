package com.smart.catalog.Repository;

import com.smart.catalog.Domain.ClassOrder;
import com.smart.catalog.Domain.StudentOrder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentOrderRepository extends AbstractRepository<StudentOrder>{

    List<StudentOrder> getStudentOrdersByStudent_Id(int id);

    List<StudentOrder> getStudentOrdersByBook_Id(int id);
}
