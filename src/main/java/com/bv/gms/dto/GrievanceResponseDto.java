package com.bv.gms.dto;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class GrievanceResponseDto {
   

	private Long id;

	
    @NotNull(message="Grievance user required")
    private Long userId;

    private String raisedBy;


    @NotBlank(message="Grievance title required")
    private String title;
    
    @NotBlank(message="Description of grievance required")
    private String description;
    
    @NotBlank(message="Type of Grievance required")
    private String grievanceType;

    @NotBlank(message="Grievance status required")
    private String status;


    @NotBlank(message="Grievance priority required")
    private String priority;    

    private LocalDate raisedOn;    

    private Long assignedTo;    
    
    private String assignedUser;    
    
    
    private String remarks;
    
    private String assignedUserRole;

	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGrievanceType() {
		return grievanceType;
	}
	public void setGrievanceType(String grievanceType) {
		this.grievanceType = grievanceType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getPriority() {
		return priority;
	}
	
	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getRaisedBy() {
		return raisedBy;
	}	
	public void setRaisedBy(String raisedBy) {
		this.raisedBy = raisedBy;
	}
	
	public Long getUserId() {
		return userId;
	}	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getId() {
		return id;
	}	
	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getRaisedOn() {
		return raisedOn;
	}	
	public void setRaisedOn(LocalDate date) {
		this.raisedOn = date;
	}
	
	public Long getAssignedTo() {
		return assignedTo;
	}	
	public void setAssignedTo(Long assignedTo) {
		this.assignedTo = assignedTo;
	}
	
	public String getRemarks() {
		return remarks;
	}	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAssignedUser() {
		return assignedUser;
	}	
	public void setAssignedUser(String assignedUser) {
		this.assignedUser = assignedUser;
	}

	public String getAssignedUserRole() {
		return assignedUserRole;
	}	
	public void setAssignedUserRole(String assignedUserRole) {
		this.assignedUserRole = assignedUserRole;
	}

	
	@Override
	public String toString() {
		return "Grievance [id=" + id + ", Description=" + description + ", Title=" + title
				+ ", Raised By=" + raisedBy + ", Status="
				+ status +  ", Priority=" + priority + ", grievance type=" + grievanceType +  ", remarks=" + remarks + "]";
	}
	
    
}
  
	