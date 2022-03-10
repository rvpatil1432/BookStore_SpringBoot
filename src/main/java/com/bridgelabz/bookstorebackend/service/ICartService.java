package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.BookDTO;

import java.util.List;

public interface ICartService {
    BookDTO addCart(BookDTO bookDTO);

    List<BookDTO> getCart();

    void deleteCart(int id);

}
