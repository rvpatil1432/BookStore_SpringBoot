package com.bridgelabz.bookstorebackend.controller;

import com.bridgelabz.bookstorebackend.dto.ForgotPasswordDTO;
import com.bridgelabz.bookstorebackend.dto.LoginDTO;
import com.bridgelabz.bookstorebackend.dto.RegisterUserDTO;
import com.bridgelabz.bookstorebackend.dto.ResetPasswordDTO;
import com.bridgelabz.bookstorebackend.service.IUserService;
import com.bridgelabz.bookstorebackend.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	private IUserService userService;

	// login API
	@PostMapping("/user/login")
	public Response login(@RequestBody LoginDTO loginDto) {
		return userService.loginUserByEmail(loginDto);
	}

	// registration API
	@PostMapping("/registeruser")
	public Response registerUser(@RequestBody RegisterUserDTO registerDto) {
		System.out.println("Register DTO printed" + registerDto);
		return userService.saveNewUser(registerDto);
	}

	// verify user API
	@GetMapping("/user/verify")
	public Object validateUser(@RequestParam String token) throws UnsupportedEncodingException {
		return userService.verifyUser(token);
	}

	// forgot password API
	@PostMapping("/user/forgot_password")
	public Response forgotPassword(@RequestBody ForgotPasswordDTO forgotPassword) throws MessagingException{
		return userService.forgotPassword(forgotPassword);
	}

	// reset password API
	@PutMapping("/user/reset_password/{token}")
	public Response resetUserPassword(@RequestBody ResetPasswordDTO resetPassword, @PathVariable String token) {
		return userService.resetPassword(resetPassword, token);
	}
	
	//Delete user API
}