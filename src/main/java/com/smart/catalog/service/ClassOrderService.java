package com.smart.catalog.service;

import com.smart.catalog.domain.Book;
import com.smart.catalog.domain.ClassOrder;
import com.smart.catalog.exception.SearchItemsNotFoundException;
import com.smart.catalog.repository.ClassOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ClassOrderService {
    private static final Logger LOG = LoggerFactory.getLogger(ClassOrderService.class);

    private final ClassOrderRepository repository;

    public ClassOrderService(ClassOrderRepository repository) {
        this.repository = repository;
    }


    public Collection<ClassOrder> getByClassId(int id) {
        return repository.getClassOrdersBySchoolClass_Id(id);
    }

    public List<ClassOrder> getAll() {
        return repository.findAll();
    }

    public int countBorrowed(Book book)
    {
        return repository.findDistinctByBook_Id(book.getId()).stream().map(o -> o.getQuantity() - o.getReturned()).mapToInt(Integer::intValue).sum();
    }

    public ClassOrder getById(int id)
    {
        return repository.findById(id).orElseThrow(SearchItemsNotFoundException::new);
    }

    public void makeOrder(ClassOrder order) {
        if (order.getId() == 0 ) {
            order.setReturned(0);
            LOG.info("На клас "+order.getSchoolClass().getName()+" записано книгу "+order.getBook().getName()+" в кількості "+order.getQuantity());
        }
        if (order.getReturned() >= order.getQuantity())
        {
            LOG.info("Клас "+order.getSchoolClass().getName()+" здав книгу "+ order.getBook().getName()+" в кількості "+ order.getQuantity());
            repository.deleteById(order.getId());
            return;
        }
        repository.save(order);
    }
}
