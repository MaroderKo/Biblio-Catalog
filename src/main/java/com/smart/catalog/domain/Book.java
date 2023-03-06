package com.smart.catalog.domain;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Book extends Domain {
    protected String name;
    protected int publishYear;
    protected String author;
    protected String publisher;
    protected BookType type;
    protected int size;
    protected int quantity;
    protected Integer classNum;
}
