package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.BookDTO;
import com.bridgelabz.bookstorebackend.entity.Book;
import com.bridgelabz.bookstorebackend.entity.User;
import com.bridgelabz.bookstorebackend.exception.BookStoreException;
import com.bridgelabz.bookstorebackend.repository.BookRepository;
import com.bridgelabz.bookstorebackend.repository.UserRepository;
import com.bridgelabz.bookstorebackend.utility.JwtUtil;
import com.bridgelabz.bookstorebackend.utility.Response;
import com.bridgelabz.bookstorebackend.utility.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService {

    @Autowired
    BookRepository bookStoreRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public Response addBook(BookDTO bookDTO, String token) {
        Book book = modelMapper.map(bookDTO, Book.class);
        User user = userRepository.findByEmail(jwtUtil.verify(token))
                .orElseThrow(() -> new BookStoreException("User Not Found.."));
        book.setUser(user);
        bookStoreRepository.save(book);
        return Utility.getResponse("Book added successfully", HttpStatus.OK);
    }

    @Override
    public Response getBook(String token) {
        User user = userRepository.findByEmail(jwtUtil.verify(token))
                .orElseThrow(() -> new BookStoreException("User Not Found.."));
      /*  return bookStoreRepository.findAll().stream().map(book -> {
            return new BookDTO(book.getBookId(), book.getBookDetails(), book.getAuthorName(), book.getBookName(),
                    book.getPrice(), book.getNoOfBooks() ,book.getImage() );
        }).collect(Collectors.toList());*/
        List<Book> books = user.getBooks().stream().collect(Collectors.toList());
        return Utility.getResponse("List of books ", books);
    }

    @Override
    public Response updateBook(BookDTO bookDTO,long bookId,String token) {
        Book book = modelMapper.map(bookDTO, Book.class);
        User user = userRepository.findByEmail(jwtUtil.verify(token))
                .orElseThrow(() -> new BookStoreException("User Not Found.."));
        bookStoreRepository.findBybookId(bookId).orElseThrow(() -> new BookStoreException("Book id not found.."));
        book.setUser(user);
        book.setBookDetails(bookDTO.getBookDetails());
        book.setAuthorName(bookDTO.getAuthorName());
        book.setBookName(bookDTO.getBookName());
        book.setPrice(bookDTO.getPrice());
        book.setNoOfBooks(bookDTO.getNoOfBooks());
        book.setImage(bookDTO.getImage());
        bookStoreRepository.save(book);
        return Utility.getResponse("Book Details updated successfully", HttpStatus.OK);
    }

    public Book findBookById(int id) {
        return bookStoreRepository.findById(id)
                .orElseThrow(() -> new BookStoreException("Unable to find any Book detail!"));
    }


    @Override
    public Response deleteBook(long bookId,String token) {
        User user = userRepository.findByEmail(jwtUtil.verify(token))
                .orElseThrow(() -> new BookStoreException("User Not Found.."));
       Book book = bookStoreRepository.findBybookId(bookId).orElseThrow(() -> new BookStoreException("Book id not found.."));
        book.setUser(user);
        bookStoreRepository.delete(book);
        return Utility.getResponse("Book deleted successfully", HttpStatus.OK);
    }

}
