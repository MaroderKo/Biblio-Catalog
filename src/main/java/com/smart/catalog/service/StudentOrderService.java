package com.smart.catalog.service;

import com.smart.catalog.domain.Book;
import com.smart.catalog.domain.Student;
import com.smart.catalog.domain.StudentOrder;
import com.smart.catalog.repository.StudentOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentOrderService {

    private static final Logger LOG = LoggerFactory.getLogger(StudentOrderService.class);

    private final StudentOrderRepository repository;

    public StudentOrderService(StudentOrderRepository repository) {
        this.repository = repository;
    }

    public List<StudentOrder> getByStudentId(int id) {
        return repository.getStudentOrdersByStudent_Id(id);
    }

    public StudentOrder findById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<StudentOrder> getByBookId(int id) {
        return repository.getStudentOrdersByBook_Id(id);
    }

    public List<StudentOrder> getAll()
    {
        return repository.findAll();
    }

    public int countBorrowed(Book book)
    {
        return repository.findDistinctByBook_Id(book.getId()).stream().map(o -> o.getQuantity() - o.getReturned()).mapToInt(Integer::intValue).sum();
    }

    public void saveOrder(int id, Book book, Student student, int quantity, int returned) {
        if (id == 0 ) {
            returned = 0;
            LOG.info("Учень "+student+" взяв книгу "+book+" в кількості "+quantity);
        }
        if (returned >= quantity)
        {
            LOG.info("Учень "+student+" повернув книгу "+book+" в кількості "+quantity);
            delete(id);
            return;
        }
        StudentOrder order = new StudentOrder(id,book, LocalDate.now(),student,quantity,returned);
        repository.save(order);
    }

    public void delete(int id)
    {
        repository.deleteById(id);
    }
}
