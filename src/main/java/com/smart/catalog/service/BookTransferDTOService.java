package com.smart.catalog.service;

import com.smart.catalog.dto.BookTransferDTO;
import org.springframework.stereotype.Component;

@Component
public class BookTransferDTOService {
    TeacherOrderService teacherOrderService;
    BorrowedOrderService borrowedOrderService;
    ClassOrderService classOrderService;

    public BookTransferDTOService(TeacherOrderService teacherOrderService, BorrowedOrderService borrowedOrderService, ClassOrderService classOrderService) {
        this.teacherOrderService = teacherOrderService;
        this.borrowedOrderService = borrowedOrderService;
        this.classOrderService = classOrderService;
    }

    public BookTransferDTO updateAmount(BookTransferDTO dto)
    {
        dto.setBalance(dto.getQuantity() - teacherOrderService.countBorrowed(dto) - borrowedOrderService.countBorrowed(dto) - classOrderService.countBorrowed(dto));
        return dto;
    }

}
