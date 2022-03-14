package com.bridgelabz.bookstorebackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
        @UniqueConstraint(columnNames = { "email" }) })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    private String DataOfBirth;
    private String Kyc;
    private int Otp;
    private String email;
    private String Password;
    private LocalDateTime RegisteredDate;
    private LocalDateTime UpdatedDate;
    private LocalDateTime PurchaseDate;
    private LocalDateTime ExpiryDate;
    @Column(name ="is_verify_email ", columnDefinition = "boolean default false")
    private boolean verifyEmail;
    @OneToMany(targetEntity=Book.class,cascade = CascadeType.ALL,mappedBy = "user")
    private List<Book> books;


}