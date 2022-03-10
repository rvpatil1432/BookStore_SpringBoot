package com.bridgelabz.bookstorebackend.controller;

import com.bridgelabz.bookstorebackend.dto.BookDTO;
import com.bridgelabz.bookstorebackend.dto.ResponseDTO;
import com.bridgelabz.bookstorebackend.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/bookStore/cart")
@Slf4j
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping(value = "/addCartDetails")
    public ResponseEntity<ResponseDTO> addCartDetails(@RequestBody BookDTO bookDTO) {
        BookDTO addCartData = cartService.addCart(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Added Cart Details", addCartData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @GetMapping(value = "/getCartDetails")
    public ResponseEntity<ResponseDTO> getCartDetails() {
        List<BookDTO> bookData = cartService.getCart();
        ResponseDTO responseDTO = new ResponseDTO("Fetched All Cart Details", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @DeleteMapping(value = "/deleteCartDetails")
    public ResponseEntity<ResponseDTO> deleteCartDetails(@RequestParam(name = "id") int id) {
        log.info(String.valueOf(id));
        cartService.deleteCart(id);
        ResponseDTO responseDTO = new ResponseDTO("Deleted by ID : Cart Details", null);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }
}

