package com.bridgelabz.bookstorebackend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Cart {

    @Id
    private int bookId;
    private String bookDetails;
    private String authorName;
    private String bookName;
    private int price;
    private int noOfBooks;
    private String image;

}
