package com.smart.catalog.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "class_order")
@NoArgsConstructor
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

    public ClassOrder(int id, SchoolClass schoolClass, LocalDate takeDate, Book book, int quantity, int returned) {
        this.id = id;
        this.schoolClass = schoolClass;
        this.takeDate = takeDate;
        this.book = book;
        this.quantity = quantity;
        this.returned = returned;
    }
}
