package com.smart.catalog.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "class_order")
public class ClassOrder extends Domain{
    @ManyToOne
    @JoinColumn(name = "school_class_id")
    private SchoolClass schoolClass;
    private LocalDate takeDate = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private int quantity;
    private int returned;
}
