package com.bv.gms.dto;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class GrievanceUpdateDto {
   

	@NotNull(message="Grievance Id required")
	private Long id;
	
	@NotNull(message="Grievance user required")
	private Long userId;

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

    private Long assignedTo;    
    
    private String remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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
    
}
  
	