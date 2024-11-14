package com.bv.gms.dto;

/*import org.springframework.security.core.GrantedAuthority;
*/
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserProfileDto {

    @NotNull
    private Long id;

    @NotBlank
    private String userId;
    
    @NotBlank
    private String salutation;
    
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;


    @NotBlank
    private String name;

    @NotBlank
    private String email;
    
    @NotBlank
    private String phone;
    
    @NotBlank
    private String role;
    
    private String department;
    

    
    
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getDepartment() {
		return role;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "UserProfileDto [id=" + id + ", userId=" + userId + ", salutation=" + salutation + ", firstName="
				+ firstName + ", lastName=" + lastName + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", role=" + role + ", department=" + department + "]";
	}
}
