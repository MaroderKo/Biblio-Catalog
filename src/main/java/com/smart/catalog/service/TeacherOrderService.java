package com.smart.catalog.service;

import com.smart.catalog.domain.Book;
import com.smart.catalog.domain.TeacherOrder;
import com.smart.catalog.repository.TeacherOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherOrderService {

    private static final Logger LOG = LoggerFactory.getLogger(TeacherOrderService.class);

    private final TeacherOrderRepository repository;

    public List<TeacherOrder> getAll()
    {
        return repository.findAll();
    }

    public TeacherOrderService(TeacherOrderRepository repository) {
        this.repository = repository;
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

    public int countBorrowed(Book book)
    {
        return repository.findDistinctByBook_Id(book.getId()).stream().map(o -> o.getQuantity() - o.getReturned()).mapToInt(Integer::intValue).sum();
    }

    public void makeOrder(TeacherOrder order) {
        if (order.getId() == 0 ) {
            order.setReturned(0);
            LOG.info("Викладач "+order.getTeacher().getPib()+" взяв книгу "+order.getBook().getName());

        }
        if (order.getReturned() >= order.getQuantity())
        {
            LOG.info("Викладач "+order.getTeacher().getPib()+" повернув книгу "+order.getBook().getName());
            repository.deleteById(order.getId());
        }
        else {
            repository.save(order);
        }


    }

}
