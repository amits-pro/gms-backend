
package com.bv.gms.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class UserRegistrationDto {  
  

  @NotBlank(message="Salutation is mandatory")
  private String salutation;

	
  @NotBlank(message="First Name is mandatory")
  private String firstName;
  
  @NotBlank(message="Last Name is mandatory")
  private String lastName;
  
  @NotBlank(message="User Id is mandatory")
  private String userId;
  
  @NotBlank(message = "Role is mandatory")
  private String role;
    
  @Size(min=10,max=10,message="Phone No must be of 10 digits")
  private String phone;

  @Email
  @NotBlank(message="Email is mandatory")
  private String email;

  @NotBlank(message="Department is mandatory")
  private String department;

  
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//  @Pattern(regexp ="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
//  message="Password must contain at least one capital, one small, one special character (@$,!,%,*,#,?,&) and should be bigger than 8 characters")
  @NotBlank(message = "Password is mandatory")
  private String password;

  
  
  
	  @Override
	public String toString() {
		return "UserRegistrationDto [firstName=" + firstName + ", lastName=" + lastName + ", userId=" + userId + ", role="
				+ role + ", phone=" + phone + ", email=" + email + ", password=" + password
				+ "]";
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	

	
}