package com.bv.gms.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class GrievanceAssignDto {

	private Long grievanceId;
	
	private Long assignedBy;

	@NotBlank(message="Type of Grievance required")
    private String grievanceType;

    @NotBlank(message="Grievance priority required")
    private String priority;    

    @NotNull(message="Grievance assignation required")
    private Long assignedTo;    
    
    private String remarks;

    private String status;

	public Long getGrievanceId() {
		return grievanceId;
	}

	public void setGrievanceId(Long grievanceId) {
		this.grievanceId = grievanceId;
	}

	public Long getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(Long assignedBy) {
		this.assignedBy = assignedBy;
	}

	
	public String getGrievanceType() {
		return grievanceType;
	}

	public void setGrievanceType(String grievanceType) {
		this.grievanceType = grievanceType;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    
}
  
	