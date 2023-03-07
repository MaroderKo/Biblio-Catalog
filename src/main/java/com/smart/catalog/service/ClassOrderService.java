package com.smart.catalog.service;

import com.smart.catalog.domain.ClassOrder;
import com.smart.catalog.repository.ClassOrderRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ClassOrderService extends AbstractService<ClassOrder, ClassOrderRepository>{

    public ClassOrderService(ClassOrderRepository repository) {
        super(repository);
    }


    public Collection<ClassOrder> getByClassId(int id) {
        return repository.getClassOrdersBySchoolClass_Id(id);
    }
}
