package com.bv.gms.entities;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/*import org.springframework.security.core.GrantedAuthority;
*/
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name="user_id")
    @NotBlank(message = "User Id is mandatory")
    private String userId;
    
    
    @Column(name="first_name")
    @NotBlank(message = "First name is mandatory")
    private String firstName;


    @Column(name="last_name")
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    
    
    @Column(name="email")
    @NotBlank(message = "Email is mandatory")
    private String email;
    
    
    @Column(name="phone")
    @NotBlank(message = "Phone number is mandatory")
    private String phone;
    
    
    @Column(name="password")
    @NotBlank(message = "Password is mandatory")
    private String password;
    
    
    @Column(name="role")
    @NotBlank(message = "Role is mandatory")
    private String role;

    @Column(name="department")
    @NotBlank(message = "Department is mandatory")
    private String department;

    @OneToMany(mappedBy = "raisedBy")
    @JsonManagedReference  // Manages serialization
    private List<Grievance> grievancess; 
   
	/*
	 * @Column(name="department_name")
	 * 
	 * @NotBlank private String departmentName;
	 */
    
	public User() {
		super();
	}
	
	public User(Long id) {
		super();
		this.id = id;
	}
	public User(Long id, String userId, String firstName, String lastName, String email, String phone,
			String password, String role, String department) {
		super();
		this.id = id;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.role = role;
		this.department = department;
		//this.departmentName = departmentName;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public List<Grievance> getGrievances() {
		return this.grievancess;
	}

	/*
	 * public String getDepartmentName() { return departmentName; } public void
	 * setDepartmentName(String departmentName) { this.departmentName =
	 * departmentName; }
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", phone=" + phone  + ", password=" + password
				+ ", role=" + role  + ", department=" + department  +"]";
	}
	/*
	 * public Collection<? extends GrantedAuthority> getAuthorities() { return null;
	 * }
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
}
