package com.smart.catalog.domain;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsExclude;

import javax.persistence.Entity;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Book extends Domain {
    protected String name;
    protected int publishYear;
    protected String author;
    protected String publisher;
    protected BookType type;
    protected int size;
    @EqualsExclude
    protected int quantity;
}
