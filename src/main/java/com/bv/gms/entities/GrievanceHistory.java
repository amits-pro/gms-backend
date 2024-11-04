package com.bv.gms.entities;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "grievance_history")
public class GrievanceHistory {
   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    @Column(name="grievance_id")
	@NotNull(message="Grievance Id is required")
	private Long grievanceId;

	@ManyToOne
    @JoinColumn(name = "modified_by", nullable = false)
	@NotNull(message="User cannot be null")
    @JsonBackReference  // Prevents infinite serialization loop
	private User modifiedBy;
   
    @Column(name = "title")
    @NotBlank(message="Grievance title required")
    private String title;
    
    @Column(name = "description")
    @NotBlank(message="Description of grievance required")
    private String description;
    
    @Column(name = "grievance_type")
    @NotBlank(message="Type of Grievance required")
    private String grievanceType;

    @Column(name = "status")
    @NotBlank(message="Status of grievance required")
    private String status;

    @Column(name = "modified_on")
    @NotNull(message="Modified On date required")
    private LocalDate modifiedOn;    

    @Column(name = "priority")
    @NotBlank(message="Grievance priority is required")
    private String priority;    
    
	@ManyToOne
    @JoinColumn(name = "assigned_to", nullable = true)
    @JsonBackReference  // Prevents infinite serialization loop
    private User assignedTo;
   
    @Column(name = "change_type")
    @NotBlank(message="Chnage type required")
    private String changeType;

    @Column(name = "remarks")
    private String remarks;    

    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getGrievanceId() {
		return grievanceId;
	}
	public void setGrievanceId(Long grievanceId) {
		this.grievanceId = grievanceId;
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

	public User getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	
	public LocalDate getRaisedOn() {
		this.modifiedOn = LocalDate.now();
		return modifiedOn;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public User getAssignedTo() {
		return assignedTo;
	}
	
	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getChangeType() {
		return changeType;
	}
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	
	public LocalDate getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(LocalDate modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Override
	public String toString() {
		return "Grievance [id=" + id + ", Description=" + description + ", Title=" + title
				+ ", Modified By=" + modifiedBy + ", Status="
				+ status +  ", Priority=" + priority +   ", grievance type=" + grievanceType + ", remarks=" + remarks + "]";
	}
	
    
}
  
	