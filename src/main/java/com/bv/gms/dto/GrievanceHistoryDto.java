package com.bv.gms.dto;
import java.time.LocalDate;


public class GrievanceHistoryDto {
   
	private Long id;
	private String raisedBy;
    private String title;
    private String description;
    private String grievanceType;
    private String status;
    private String priority;    
    private LocalDate raisedOn;    
    private String assignedTo;    
    private String modifiedBy;
    private String remarks;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRaisedBy() {
		return raisedBy;
	}
	public void setRaisedBy(String raisedBy) {
		this.raisedBy = raisedBy;
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
	public LocalDate getRaisedOn() {
		return raisedOn;
	}
	public void setRaisedOn(LocalDate raisedOn) {
		this.raisedOn = raisedOn;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
}
  
	