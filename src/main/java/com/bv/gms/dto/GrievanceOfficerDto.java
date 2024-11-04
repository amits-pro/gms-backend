package com.bv.gms.dto;

public class GrievanceOfficerDto {
	
	public Long id;
	public String role;
	public String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GrievanceOfficerDto(Long id, String role, String name) {
		super();
		this.id = id;
		this.role = role;
		this.name = name;
	}
	
	

}
