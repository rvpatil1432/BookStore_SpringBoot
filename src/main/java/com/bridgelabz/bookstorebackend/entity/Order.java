package com.bridgelabz.bookstorebackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name="order_details")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    @Column(name = "number_book_order")
    private String numberOfBooksOrdered;

    @Column(name = "book_total_price")
    private String totalPrice;
}