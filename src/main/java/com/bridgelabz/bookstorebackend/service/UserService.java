package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.UserDTO;
import com.bridgelabz.bookstorebackend.entity.User;
import com.bridgelabz.bookstorebackend.exception.BookStoreException;
import com.bridgelabz.bookstorebackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User addDetails = modelMapper.map(userDTO, User.class);
        userRepository.save(addDetails);
        return userDTO;
    }

    @Override
    public List<UserDTO> getUserDetails() {
        return userRepository.findAll().stream().map(user -> {
            return new UserDTO(user.getId(), user.getUserName(), user.getMobileNo(), user.getAddress(), user.getPinCode(),
                                user.getEmail(),user.getPassword(), user.getCity(), user.getState(), user.getType());
        }).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(int id, UserDTO userDTO) {
        UserDTO userResponse = null;
        if (id > 0) {
            User userDetails = findBookById(id);
            String[] ignoreFields = {"id"};
            BeanUtils.copyProperties(userDTO, userDetails, ignoreFields);
            userRepository.save(userDetails);
            userResponse = modelMapper.map(userDetails, UserDTO.class);
        }
        return userResponse;
    }

    public User findBookById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BookStoreException("Unable to find any Person contact detail!"));
    }


    @Override
    public void deleteUser(int id) {
        if (id > 0) {
            User contact = findBookById(id);
            userRepository.delete(contact);
        }
    }

    @Override
    public User getUserContact(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
