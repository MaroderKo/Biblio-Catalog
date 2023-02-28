package com.smart.catalog.DTO;

import com.smart.catalog.Domain.Book;
import com.smart.catalog.Domain.BookType;
import lombok.Data;

@Data
public class BookDTO extends Book {
    public BookDTO(Book b)
    {
        setId(b.getId());
        setName(b.getName());
        setPublishYear(b.getPublishYear());
        setAuthor(b.getAuthor());
        setPublisher(b.getPublisher());
        setType(b.getType());
        setSize(b.getSize());
        setQuantity(b.getQuantity());
        setClassNum(b.getClassNum());
    }
    int balance;
}
