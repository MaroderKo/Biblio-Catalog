package com.smart.catalog.service;

import com.smart.catalog.domain.Book;
import com.smart.catalog.domain.BorrowedOrder;
import com.smart.catalog.repository.BorrowedOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class BorrowedOrderService{

    BorrowedOrderRepository repository;

    public BorrowedOrderService(BorrowedOrderRepository repository) {
        this.repository = repository;
    }

    public int countBorrowed(Book book)
    {
        return repository.countDistinctByBook_Id(book.getId());
    }
}
