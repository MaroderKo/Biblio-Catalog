package com.smart.catalog.dto;

import com.smart.catalog.domain.Book;
import com.smart.catalog.service.ClassOrderService;
import com.smart.catalog.service.StudentOrderService;
import com.smart.catalog.service.TeacherOrderService;
import lombok.Getter;

@Getter
public final class BookTransferDTO extends Book {

    private final int balance;

    public BookTransferDTO(Book book, StudentOrderService studentOrderService, TeacherOrderService teacherOrderService, ClassOrderService classOrderService) {
        super(book.getName(), book.getPublishYear(), book.getAuthor(), book.getPublisher(), book.getType(), book.getSize(), book.getQuantity());
        balance = getQuantity() - teacherOrderService.countBorrowed(book) - studentOrderService.countBorrowed(book) - classOrderService.countBorrowed(book);
    }
}
