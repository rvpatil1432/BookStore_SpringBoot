package com.bridgelabz.bookstorebackend.service;


import com.bridgelabz.bookstorebackend.dto.ForgotPasswordDTO;
import com.bridgelabz.bookstorebackend.dto.LoginDTO;
import com.bridgelabz.bookstorebackend.dto.RegisterUserDTO;
import com.bridgelabz.bookstorebackend.dto.ResetPasswordDTO;
import com.bridgelabz.bookstorebackend.entity.User;
import com.bridgelabz.bookstorebackend.exception.BookStoreException;
import com.bridgelabz.bookstorebackend.repository.UserRepository;
import com.bridgelabz.bookstorebackend.utility.JavaMailUtil;
import com.bridgelabz.bookstorebackend.utility.JwtUtil;
import com.bridgelabz.bookstorebackend.utility.Response;
import com.bridgelabz.bookstorebackend.utility.Utility;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	JavaMailUtil javaMailUtil;

	// Register new user
	@Override
	public Response saveNewUser(RegisterUserDTO registrationDto) {
		userRepository.findByEmail(registrationDto.getEmail()).ifPresent(registeredUser -> {
			throw new BookStoreException("Oops...User is already present");
		});
		User user = modelMapper.map(registrationDto, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User save = userRepository.save(user);
		String jwtToken = jwtUtil.createJwtToken(save.getEmail());
		javaMailUtil.sendMail(save.getEmail(), jwtToken);
		return Utility.getResponse("User Registerd Successfully, check mail to activate account", jwtToken);
	}

	// Verify token
	@Override
	public Response verifyUser(String token) {
		User user = userRepository.findByEmail(jwtUtil.verify(token))
				.orElseThrow(() -> new BookStoreException("User Not Found.."));
		user.setVerifyEmail(true);
		userRepository.save(user);
		return Utility.getResponse("User Verified Successfully", HttpStatus.OK);
	}

	// login by email and password
	@Override
	public Response loginUserByEmail(LoginDTO loginDTO) {
		System.out.println("........"+loginDTO.getEmail());
		User user = userRepository.findByEmail(loginDTO.getEmail())
				.orElseThrow(() -> new BookStoreException("Invalid Email Id"));
		if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
			String jwtToken = jwtUtil.createJwtToken(loginDTO.getEmail());
			return Utility.getResponse("Login Successfully...", jwtToken);
		}
		return Utility.getResponse("Invalid Password....", HttpStatus.BAD_REQUEST);
	}

	// Forgot password
	@Override
	public Response forgotPassword(ForgotPasswordDTO forgotPassword) {
		User user = userRepository.findByEmail(forgotPassword.getEmail())
				.orElseThrow(() -> new BookStoreException("User Account Not Found.."));
		User save = userRepository.save(user);
		String jwtToken = jwtUtil.createJwtToken(save.getEmail());
		javaMailUtil.resetPasswordMail(save.getEmail(), jwtToken);
		return Utility.getResponse("Email has been sent to your account", jwtToken);
	}

	// Reset Password
	@Override
	public Response resetPassword(ResetPasswordDTO resetPassword, String token) {
		if (resetPassword.getPassword().equals(resetPassword.getConfirm_password())) {
			User user = userRepository.findByEmail(jwtUtil.verify(token))
					.orElseThrow(() -> new BookStoreException("User Not Found.."));
			if (user.isVerifyEmail()) {
				user.setPassword(passwordEncoder.encode(resetPassword.getPassword()));
				userRepository.save(user);
			}
			return Utility.getResponse("Password changed successfully", HttpStatus.OK);
		}
		return Utility.getResponse("Password doesn't match...", HttpStatus.BAD_REQUEST);
	}

}// end
