package com.bridgelabz.bookstorebackend.controller;

import com.bridgelabz.bookstorebackend.dto.BookDTO;
import com.bridgelabz.bookstorebackend.dto.ResponseDTO;
import com.bridgelabz.bookstorebackend.service.BookService;
import com.bridgelabz.bookstorebackend.utility.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/bookStore/book")
@Slf4j
public class BookController {

    @Autowired
    BookService bookService;

    /*
    Purpose : To add book details in bookstore database.
     */
    @PostMapping(value = "/addBookDetails")
    public Response addBookDetails(@RequestBody BookDTO bookDTO, @RequestHeader String token) {
        return bookService.addBook(bookDTO,token);
    }

    /*
   Purpose : To get the all book details from bookstore database.
    */
    @GetMapping("/getBookDetails")
    public Response getBookDetails(@RequestHeader String token) {
        return bookService.getBook(token);
    }

    /*
   Purpose : To edit book details in bookstore database.
    */
    @PutMapping(value = "/updateBookDetails")
    public Response updateBookDetails(@RequestBody BookDTO bookDTO, @RequestParam long id, @RequestHeader String token) {
        return bookService.updateBook(bookDTO,id,token);
    }

    /*
  Purpose : To delete book details in bookstore database.
   */
    @DeleteMapping(value = "/deleteBookDetails")
    public Response deleteBookDetails(@RequestParam long bookId, @RequestHeader String token) {
        return bookService.deleteBook(bookId, token);
    }


}


