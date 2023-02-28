package com.smart.catalog.Repository;

import com.smart.catalog.Domain.StudentOrder;
import com.smart.catalog.Domain.TeacherOrder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TeacherOrderRepository extends AbstractRepository<TeacherOrder>{

    List<TeacherOrder> getTeacherOrdersByTeacher_Id(int id);

    List<TeacherOrder> getTeacherOrdersByBook_Id(int id);
}
