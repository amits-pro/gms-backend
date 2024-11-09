package com.bv.gms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateUserProfileDto {

	@NotBlank(message = "Email cannot be blank")
    private Long id;

	@NotBlank(message = "Email cannot be blank")
    private String email;
    
    @NotBlank(message = "Phone number cannot be blank")
    @Size(min=10,max=10,message="Phone No must be of 10 digits")
    private String phone;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

    
    
	
	
}
