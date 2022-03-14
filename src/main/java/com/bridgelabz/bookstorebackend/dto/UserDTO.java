package com.bridgelabz.bookstorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String name;
    private String username;
    private String Kyc;
    private String dataOfBirth;
    private int otp;
    private String Email;
    private String Password;
/*
    private int id;

    private String userName;
    private String mobileNo;
    private String address;
    private String pinCode;
    private String email;
    private String password;
    private String city;
    private String state;
    private String type;*/

}