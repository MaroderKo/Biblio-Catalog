package com.smart.catalog.repository;

import com.smart.catalog.domain.BorrowedOrder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BorrowedOrderRepository extends AbstractRepository<BorrowedOrder>{

    List<BorrowedOrder> findDistinctByBook_Id(int id);
}
