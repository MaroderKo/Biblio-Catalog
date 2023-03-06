package com.smart.catalog.service;

import com.smart.catalog.domain.BorrowedOrder;
import com.smart.catalog.repository.BorrowedOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class BorrowedOrderService extends AbstractService<BorrowedOrder, BorrowedOrderRepository>{

    public BorrowedOrderService(BorrowedOrderRepository repository) {
        super(repository);
    }
}
