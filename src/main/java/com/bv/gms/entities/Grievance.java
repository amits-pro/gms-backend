package com.bv.gms.entities;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "grievance")
public class Grievance {
   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
   
	@ManyToOne
    @JoinColumn(name = "raised_by", nullable = false)
	@NotNull(message="User cannot be null")
    @JsonBackReference  // Prevents infinite serialization loop
	private User raisedBy;
   
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

    @Column(name = "raised_on")
    private LocalDate raisedOn;    

    @Column(name = "priority")
    @NotBlank(message="Grievance priority is required")
    private String priority;    
    
	@ManyToOne
    @JoinColumn(name = "assigned_to", nullable = true)
    @JsonBackReference  // Prevents infinite serialization loop
    private User assignedTo;

    @Column(name = "updatedOn")
    private LocalDate updatedOn;

	@ManyToOne
    @JoinColumn(name = "updated_by", nullable = false)
	@NotNull(message="User cannot be null")
    @JsonBackReference  // Prevents infinite serialization loop
    private User updatedby;

    @Column(name = "remarks")
    private String remarks;    

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getRaisedBy() {
		return raisedBy;
	}

	public void setRaisedBy(User raisedBy) {
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

	public LocalDate getRaisedOn() {
		return raisedOn;
	}

	public void setRaisedOn(LocalDate raisedOn) {
		this.raisedOn = raisedOn;
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

	public LocalDate getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDate updatedOn) {
		this.updatedOn = updatedOn;
	}

	public User getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(User updatedby) {
		this.updatedby = updatedby;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public Grievance() {}	
	
	public Grievance(String title, String description, String priority, String type, 
						String status) 
	{
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.grievanceType = type;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Grievance [id=" + id + ", Description=" + description + ", Title=" + title
				+ ", Raised By=" + raisedBy + ", Status="
				+ status +  ", Priority=" + priority +   ", grievance type=" + grievanceType +"]";
	}
	
    
}
  
	