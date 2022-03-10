package com.bridgelabz.bookstorebackend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;

    private String bookDetails;
    private String authorName;
    private String bookName;
    private int price;
    private int noOfBooks;

    @Column(name = "image")
    private String image;
}