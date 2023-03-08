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
@Table(name = "teacher_order")
@NoArgsConstructor
public class TeacherOrder extends Domain{
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private LocalDate takeDate = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    private int quantity;
    private int returned;

    public TeacherOrder(int id, Book book, LocalDate takeDate, Teacher teacher, int quantity, int returned) {
        this.id = id;
        this.book = book;
        this.takeDate = takeDate;
        this.teacher = teacher;
        this.quantity = quantity;
        this.returned = returned;
    }
}
