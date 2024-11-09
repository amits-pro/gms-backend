package com.bv.gms.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DashboardDto {

	String dashboardTitle;
	
	Integer overAllGrievanceCount;
	Integer newGrievanceCount;
	Integer assignedGrievanceCount;
	Integer inProgessGrievanceCount;
	Integer declinedGrievanceCount;
	Integer resolvedGrievanceCount;

	List<NameValue> grievancesInDateRange;
	List<NameValue> grievancesByDepartmentInDateRange;
	List<NameValue> grievancesByStatusInDateRange;
	List<NameValue> overallGrievancesByStatus;
	List<NameValue> overallGrievancesByDate;
	List<NameValue> overallGrievancesByDepartments;

	public Integer getOverAllGrievanceCount() {
		return overAllGrievanceCount;
	}
	public void setOverAllGrievanceCount(Integer overAllGrievanceCount) {
		this.overAllGrievanceCount = overAllGrievanceCount;
	}
	public List<NameValue> getGrievancesInDateRange() {
		return grievancesInDateRange;
	}
	public void setGrievancesInDateRange(List<NameValue> grievancesInDateRange) {
		this.grievancesInDateRange = grievancesInDateRange;
	}
	public List<NameValue> getGrievancesByDepartmentInDateRange() {
		return grievancesByDepartmentInDateRange;
	}
	public void setGrievancesByDepartmentInDateRange(List<NameValue> grievancesByDepartmentInDateRange) {
		this.grievancesByDepartmentInDateRange = grievancesByDepartmentInDateRange;
	}
	public List<NameValue> getGrievancesByStatusInDateRange() {
		return grievancesByStatusInDateRange;
	}
	public void setGrievancesByStatusInDateRange(List<NameValue> grievancesByStatusInDateRange) {
		this.grievancesByStatusInDateRange = grievancesByStatusInDateRange;
	}
	public List<NameValue> getOverallGrievancesByStatus() {
		return overallGrievancesByStatus;
	}
	public void setOverallGrievancesByStatus(List<NameValue> overallGrievancesByStatus) {
		this.overallGrievancesByStatus = overallGrievancesByStatus;
	}
	public List<NameValue> getOverallGrievancesByDate() {
		return overallGrievancesByDate;
	}
	public void setOverallGrievancesByDate(List<NameValue> overallGrievancesByDate) {
		this.overallGrievancesByDate = overallGrievancesByDate;
	}
	public List<NameValue> getOverallGrievancesByDepartments() {
		return overallGrievancesByDepartments;
	}
	public void setOverallGrievancesByDepartments(List<NameValue> overallGrievancesByDepartments) {
		this.overallGrievancesByDepartments = overallGrievancesByDepartments;
	}
	public Integer getNewGrievanceCount() {
		return newGrievanceCount;
	}
	public void setNewGrievanceCount(Integer newGrievanceCount) {
		this.newGrievanceCount = newGrievanceCount;
	}
	public Integer getAssignedGrievanceCount() {
		return assignedGrievanceCount;
	}
	public void setAssignedGrievanceCount(Integer assignedGrievanceCount) {
		this.assignedGrievanceCount = assignedGrievanceCount;
	}
	public Integer getInProgessGrievanceCount() {
		return inProgessGrievanceCount;
	}
	public void setInProgessGrievanceCount(Integer inProgessGrievanceCount) {
		this.inProgessGrievanceCount = inProgessGrievanceCount;
	}
	public Integer getDeclinedGrievanceCount() {
		return declinedGrievanceCount;
	}
	public void setDeclinedGrievanceCount(Integer declinedGrievanceCount) {
		this.declinedGrievanceCount = declinedGrievanceCount;
	}
	public Integer getResolvedGrievanceCount() {
		return resolvedGrievanceCount;
	}
	public void setResolvedGrievanceCount(Integer resolvedGrievanceCount) {
		this.resolvedGrievanceCount = resolvedGrievanceCount;
	}
	public String getDashboardTitle() {
		return dashboardTitle;
	}
	public void setDashboardTitle(String dashboardTitle) {
		this.dashboardTitle = dashboardTitle;
	}	
}
