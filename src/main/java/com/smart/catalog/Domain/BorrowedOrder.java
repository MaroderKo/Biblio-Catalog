package com.smart.catalog.Domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
    Book book;
    String counterAgent;
    int quantity;
    boolean returned;
    Date borrowDate = new Date(System.currentTimeMillis());
    Date returnDate;
}
