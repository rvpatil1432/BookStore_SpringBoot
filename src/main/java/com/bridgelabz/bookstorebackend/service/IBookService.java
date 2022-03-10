package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.BookDTO;
import java.util.List;

public interface IBookService {

    BookDTO addBook(BookDTO bookDTO);
    List<BookDTO> getBook();
    BookDTO updateBook(int id, BookDTO bookDTO);
    void deleteBook(int id);
    BookDTO getBookByID(int id);

}
