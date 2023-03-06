package com.smart.catalog.service;

import com.smart.catalog.domain.StudentOrder;
import com.smart.catalog.repository.StudentOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentOrderService extends AbstractService<StudentOrder, StudentOrderRepository>{

    public StudentOrderService(StudentOrderRepository repository) {
        super(repository);
    }

    public List<StudentOrder> getByStudentId(int id)
    {
        return repository.getStudentOrdersByStudent_Id(id);
    }
    public StudentOrder findById(int id)
    {
        return repository.findById(id).orElse(null);
    }

    public List<StudentOrder> getByBookId(int id) {
        return repository.getStudentOrdersByBook_Id(id);
    }
}
