package com.smart.catalog.dto;

import com.smart.catalog.domain.Book;
import com.smart.catalog.repository.StudentOrderRepository;
import com.smart.catalog.repository.TeacherOrderRepository;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Data
@Immutable
public class BookTransferDTO extends Book {

    private int balance;

    public BookTransferDTO(Book book) {
        super(book.getName(), book.getPublishYear(), book.getAuthor(), book.getPublisher(), book.getType(), book.getSize(), book.getQuantity());
    }
}
