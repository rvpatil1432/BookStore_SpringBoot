package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.BookDTO;
import com.bridgelabz.bookstorebackend.utility.Response;

import java.util.List;

public interface IBookService {

    Response addBook(BookDTO bookDTO, String token);
    Response getBook(String token);
    Response updateBook(BookDTO bookDTO,long bookId,String token);
    Response deleteBook(long bookId,String token);
}
