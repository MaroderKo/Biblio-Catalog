package com.smart.catalog.Domain;

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
    SchoolClass schoolClass;
    LocalDate takeDate = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;
    int quantity;
    int returned;
}
