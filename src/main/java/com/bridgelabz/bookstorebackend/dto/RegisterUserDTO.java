package com.bridgelabz.bookstorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDTO {
	
	private String name;
	private String username;
	private String password;
	private String email;

	@Override
	public String toString() {
		return "RegisterUserDTO [name=" + name + ", username=" + username + ", password=" + password + ", email="
				+ email + "]";
	}

}
