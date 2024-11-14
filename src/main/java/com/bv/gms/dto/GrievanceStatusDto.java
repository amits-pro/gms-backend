package com.bv.gms.dto;

import java.util.List;

public class GrievanceStatusDto {
	
	DashboardDto dashboard;
	List<GrievanceResponseDto> grievances;
	public DashboardDto getDashboard() {
		return dashboard;
	}
	
	public void setDashboard(DashboardDto dashboard) {
		this.dashboard = dashboard;
	}
	public List<GrievanceResponseDto> getGrievances() {
		return grievances;
	}
	public void setGrievances(List<GrievanceResponseDto> grievances) {
		this.grievances = grievances;
	}
	
	

}
