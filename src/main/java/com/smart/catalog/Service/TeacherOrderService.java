package com.smart.catalog.Service;

import com.smart.catalog.Domain.TeacherOrder;
import com.smart.catalog.Repository.TeacherOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherOrderService extends AbstractService<TeacherOrder, TeacherOrderRepository>{

    public TeacherOrderService(TeacherOrderRepository repository) {
        super(repository);
    }

    public List<TeacherOrder> getByTeacherId(int id)
    {
        return repository.getTeacherOrdersByTeacher_Id(id);
    }

    public TeacherOrder findById(int id)
    {
        return repository.findById(id).orElse(null);
    }


    public List<TeacherOrder> getByBookId(int id) {
        return repository.getTeacherOrdersByBook_Id(id);
    }
}
