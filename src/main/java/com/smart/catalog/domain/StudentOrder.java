package com.smart.catalog.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "student_order")
@NoArgsConstructor
public class StudentOrder extends Domain{
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private LocalDate takeDate = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    private int quantity;
    private int returned = 0;

    public StudentOrder(int id, Book book, LocalDate takeDate, Student student, int quantity, int returned) {
        this.id = id;
        this.book = book;
        this.takeDate = takeDate;
        this.student = student;
        this.quantity = quantity;
        this.returned = returned;
    }
}
