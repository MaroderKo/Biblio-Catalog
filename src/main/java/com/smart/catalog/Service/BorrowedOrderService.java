package com.smart.catalog.Service;

import com.smart.catalog.Domain.Book;
import com.smart.catalog.Domain.BorrowedOrder;
import com.smart.catalog.Repository.AbstractRepository;
import com.smart.catalog.Repository.BorrowedOrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BorrowedOrderService extends AbstractService<BorrowedOrder, BorrowedOrderRepository>{

    public BorrowedOrderService(BorrowedOrderRepository repository) {
        super(repository);
    }
}
