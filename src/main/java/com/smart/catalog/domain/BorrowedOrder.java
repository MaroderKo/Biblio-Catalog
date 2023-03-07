package com.smart.catalog.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "borrowed_order")
public class BorrowedOrder extends Domain{
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private String counterAgent;
    private int quantity;
    private boolean returned;
    private Date borrowDate = new Date(System.currentTimeMillis());
    private Date returnDate;
}
