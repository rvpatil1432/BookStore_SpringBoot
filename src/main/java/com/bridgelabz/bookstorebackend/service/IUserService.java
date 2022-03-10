package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.UserDTO;
import com.bridgelabz.bookstorebackend.entity.User;
import java.util.List;

public interface IUserService {
    UserDTO addUser(UserDTO userDTO);

    List<UserDTO> getUserDetails();

    UserDTO updateUser(int id, UserDTO userDTO);

    void deleteUser(int id);

    User getUserContact(String email, String password);
}
