package com.smart.catalog.service;

import com.smart.catalog.domain.Book;
import com.smart.catalog.domain.BorrowedOrder;
import com.smart.catalog.repository.BorrowedOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class BorrowedOrderService{

    private final BorrowedOrderRepository repository;

    public BorrowedOrderService(BorrowedOrderRepository repository) {
        this.repository = repository;
    }

    //TODO: Complete business logic with reverse borrowing
    public int countBorrowed(Book book)
    {
        return repository.findDistinctByBook_Id(book.getId()).stream().map(BorrowedOrder::getQuantity).mapToInt(Integer::intValue).sum();
    }
}
