package com.smart.catalog.Domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "student_order")
public class StudentOrder extends Domain{
    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;
    LocalDate takeDate = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;
    int quantity;
    int returned = 0;
}
