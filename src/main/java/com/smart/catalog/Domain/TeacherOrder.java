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
@Table(name = "teacher_order")
public class TeacherOrder extends Domain{
    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;
    LocalDate takeDate = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    Teacher teacher;
    int quantity;
    int returned = 0;
}
