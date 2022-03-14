package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.ForgotPasswordDTO;
import com.bridgelabz.bookstorebackend.dto.LoginDTO;
import com.bridgelabz.bookstorebackend.dto.RegisterUserDTO;
import com.bridgelabz.bookstorebackend.dto.ResetPasswordDTO;
import com.bridgelabz.bookstorebackend.utility.Response;

public interface IUserService {

	Response loginUserByEmail(LoginDTO loginDTO); // Login user by mail

	Response saveNewUser(RegisterUserDTO registrationDto); // Registration

	Object verifyUser(String token); // Verify user using token

	Response resetPassword(ResetPasswordDTO resetPassword, String token); // Reset Password

	Response forgotPassword(ForgotPasswordDTO forgotPassword); // Forget Password

}
