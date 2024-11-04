package com.bv.gms.dto;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Table
public class LoginDto {
	
	@Column(name="user_id")
	@NotBlank()
   String userId;
	
	@Column(name="password")
	@NotBlank
   String password;
   
   public LoginDto(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDto [userId=" + userId + ", password=" + password + "]";
	}
	
	

}
