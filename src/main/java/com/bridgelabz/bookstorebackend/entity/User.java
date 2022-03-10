package com.bridgelabz.bookstorebackend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String userName;
    private String mobileNo;
    private String address;
    private String pinCode;
    private String email;
    private String password;
    private String city;
    private String state;
    private String type;

}