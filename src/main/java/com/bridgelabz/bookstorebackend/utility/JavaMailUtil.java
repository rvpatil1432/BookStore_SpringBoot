package com.bridgelabz.bookstorebackend.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
@Component
public class JavaMailUtil {
	
	    @Autowired
	    JavaMailSender javaMailSender;
        
	    // Send mail for account verification when new user will register
	    public SimpleMailMessage sendMail(String email, String jwtToken) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        String link = "http://localhost:8080/user/verify?token=";
	        message.setTo(email);
	        message.setSubject("Account verification mail");
	        message.setText("Registration Successful to activate your account click on this link   " + (link + jwtToken));
	        javaMailSender.send(message);
	        return message;
	    }
	    
	    //Send mail for when user request for forget password and then reset password 
	    public SimpleMailMessage resetPasswordMail(String email, String jwtToken) {
	        SimpleMailMessage message = new SimpleMailMessage();
//          String link = "http://localhost:8080/user/reset_password/";
	        String link2 = "http://localhost:8080/user/reset_password/";
	        message.setTo(email);
	        message.setSubject("Password Reset Request");
            message.setText("To reset your password, click the link	"  + (link2 + jwtToken ));
//	        message.setText("Now change the password " + ( resetPage);
	        javaMailSender.send(message);
	        return message;
	    }
	    	    
}
