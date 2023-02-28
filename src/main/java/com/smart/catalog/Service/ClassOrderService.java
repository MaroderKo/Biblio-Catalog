package com.smart.catalog.Service;

import com.smart.catalog.Domain.Book;
import com.smart.catalog.Domain.ClassOrder;
import com.smart.catalog.Repository.AbstractRepository;
import com.smart.catalog.Repository.ClassOrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class ClassOrderService extends AbstractService<ClassOrder, ClassOrderRepository>{

    public ClassOrderService(ClassOrderRepository repository) {
        super(repository);
    }


    public Collection<ClassOrder> getByClassId(int id) {
        return repository.getClassOrdersBySchoolClass_Id(id);
    }
}
