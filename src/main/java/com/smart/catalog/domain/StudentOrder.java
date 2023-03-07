package com.smart.catalog.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "student_order")
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
}
