package com.smart.catalog.dto;

import com.smart.catalog.domain.Book;
import lombok.Data;

@Data
public class BookTransferDTO extends Book {
    public BookTransferDTO(Book b)
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
    private int balance;
}
