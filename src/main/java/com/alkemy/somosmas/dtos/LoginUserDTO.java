package com.alkemy.somosmas.dtos;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginUserDTO {
	@NotBlank(message = "Username is mandatory")
	@Email(message = "Username should be a valid email")
	private String username;
	@NotBlank(message = "Password is mandatory")
	private String password;

	public String getUsername() {
		return username;
	}
	public void setUsername(String email) {
		this.username = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
