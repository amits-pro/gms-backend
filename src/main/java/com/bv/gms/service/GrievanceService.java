package com.bv.gms.service;

import java.io.Console;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bv.gms.dto.DashboardDto;
import com.bv.gms.dto.GrievanceAssignDto;
import com.bv.gms.dto.GrievanceDto;
import com.bv.gms.dto.GrievanceHistoryDto;
import com.bv.gms.dto.GrievanceResponseDto;
import com.bv.gms.dto.GrievanceStatusDto;
import com.bv.gms.dto.GrievanceUpdateDto;
import com.bv.gms.dto.NameValue;
import com.bv.gms.entities.Grievance;
import com.bv.gms.entities.GrievanceHistory;
import com.bv.gms.entities.User;
import com.bv.gms.exceptions.BusinessException;
import com.bv.gms.repositories.GrievanceHistoryRepository;
import com.bv.gms.repositories.GrievanceRepository;
import com.bv.gms.repositories.UserRepository;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import jakarta.transaction.Transactional;


@Service
public class GrievanceService {

	@Autowired
	GrievanceRepository grievanceRepository;

	@Autowired
	GrievanceHistoryRepository grievancehistoryRepository;

	@Autowired
	UserRepository userRepository;

	@Transactional
	public Grievance insert(GrievanceDto dto) {
		Grievance insertedGrievance = grievanceRepository.save(newGrievance("INSERT", dto));
		grievancehistoryRepository.save(getGrievanceHistory("INSERT", insertedGrievance));
		return insertedGrievance;
	}
	
	@Transactional
	public Grievance assign(GrievanceAssignDto dto) {
		Grievance oldGrievance = grievanceRepository.findById(dto.getGrievanceId()).get();
		if(oldGrievance != null) {
			Grievance assignedGrievance = updateAssignation(dto);
			Grievance updatedGrievance = grievanceRepository.save(assignedGrievance);
			grievancehistoryRepository.save(getGrievanceHistory("UPDATE", oldGrievance));
			return updatedGrievance;
		}
		return null;
	}
	
	@Transactional
	public Grievance update(GrievanceUpdateDto dto) {
		Grievance oldGrievance = grievanceRepository.findById(dto.getId()).get();
		Grievance updatedGrievance = null;
		if(oldGrievance != null) {
			updatedGrievance = updateGrievance(dto);
			updatedGrievance = grievanceRepository.save(updatedGrievance);
			grievancehistoryRepository.save(getGrievanceHistory("UPDATE", oldGrievance));
		}
		return updatedGrievance;
	}
	
	public List<GrievanceHistoryDto> getHistory(Long grievanceId) {

		Grievance grievance = grievanceRepository.findById(grievanceId).get();
		List<GrievanceHistoryDto> dtoList = new ArrayList<GrievanceHistoryDto>();
		List<GrievanceHistory> gvhist = grievancehistoryRepository.getByGrievanceId(grievanceId);
		for(GrievanceHistory gh: gvhist) {
			System.out.println(gh);
			dtoList.add(getGrievanceHistory(grievance, gh));
		}
		return dtoList;
	}
	public List<Grievance> getAll(Long userId) {
		
		User user = userRepository.findById(userId).get();
		System.out.println(user); 
		if(! user.getRole().equals("Grivance Controller Grievance Controller")) {
			return grievanceRepository.getByUserId(userId);
		} else {
			return grievanceRepository.findAll();
		}
    }
	
	
	public DashboardDto getOfficersDashBoardData(String from, String to, Long userId ) {

		User user = userRepository.findById(userId).get();
		DashboardDto dto = new DashboardDto();
		List<Object[]> overAllData = getOverAllData(userId,  user.getRole());
		List<Object[]> dateRangeData = getDateRangeData(userId,  from, to, user.getRole());
		setReportsData(userId, dto, overAllData, false);
		setReportsData(userId, dto, dateRangeData, true);
		
		if(user.getRole().equals("Grievance Controller")) {
			dto.setDashboardTitle("Grievance Controller Dashboard");
		} else {
			dto.setDashboardTitle(user.getDepartment() + " Grievance Dashboard");
		}
		
		return dto;
	}
	
	
	public GrievanceStatusDto getUsersDashBoardData(Long userId) {
		
		GrievanceStatusDto statusDto = new GrievanceStatusDto();

		List<Grievance> grievances = grievanceRepository.getByUserId(userId);
		List<GrievanceResponseDto> grievanceReponseDtos = new ArrayList<GrievanceResponseDto>(); 
		for(Grievance g: grievances) {
			grievanceReponseDtos.add(getGrievanceDto(g));
		}
		statusDto.setGrievances(grievanceReponseDtos);
		
		
		DashboardDto dashBoardDto = new DashboardDto();
		statusDto.setDashboard(dashBoardDto);
		List<Object[]> reportsData = getOverAllData(userId, "grievance-user");
		setReportsData(userId, dashBoardDto, reportsData, false);

		return statusDto;
	}
	
	private GrievanceResponseDto getGrievanceDto(Grievance g) {
		GrievanceResponseDto dto = new GrievanceResponseDto();
		dto.setId(g.getId());
		dto.setRaisedOn(g.getRaisedOn());
		dto.setRaisedBy(g.getRaisedBy().getFirstName() + " " + g.getRaisedBy().getLastName()  );
		dto.setTitle(g.getTitle());
		dto.setDescription(g.getDescription());
		dto.setPriority(g.getPriority());	
		dto.setGrievanceType(g.getGrievanceType());
		dto.setStatus(g.getStatus());
		dto.setRemarks(g.getRemarks());		
		if(g.getAssignedTo() != null) {
			dto.setAssignedTo(g.getAssignedTo().getId());		
			dto.setAssignedUser(g.getAssignedTo().getFirstName() + " " + g.getAssignedTo().getLastName());
			dto.setAssignedUserRole(g.getAssignedTo().getRole());
		}
		return dto;
	}
	
	private List<Object[]> getOverAllData(Long userId, String userType) {

		User user = userRepository.findById(userId).get();
		List<Object[]> reportsData = null;
		switch(userType ) {
		case "grievance-user":
			reportsData = grievanceRepository.findOverallGrievancesDataForUser(userId);
			break;
		case "Grievance Controller":
			reportsData = grievanceRepository.findOverallGrievancesData();
			break;
		case "Grievance Supervisor":
			reportsData = grievanceRepository.findOverallGrievancesDataByDepartment(user.getDepartment());
			break;
		case "Grievance Officer":
			reportsData = grievanceRepository.findOverallGrievancesDataByDepartment(user.getDepartment());
			break;
		}
		return reportsData;
	}
	
	private List<Object[]> getDateRangeData(Long userId, String from, String to, String userType) {

		User user = userRepository.findById(userId).get();
		String role = user.getRole();
		String department = user.getDepartment();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fromDate = LocalDate.parse(from,formatter);
		LocalDate toDate = LocalDate.parse(to,formatter);
		List<Object[]> reportsData = null;

		switch(userType ) {
		case "grievance-user":
			break;
		case "Grievance Controller":
			reportsData = grievanceRepository.findGrievancesDataInDateRange(fromDate, toDate);
			break;
		case "Grievance Supervisor":
			reportsData = grievanceRepository.findGrievancesDataInDateRangeByDepartment(fromDate, toDate, user.getDepartment());
			break;
		case "Grievance Officer":
			reportsData = grievanceRepository.findGrievancesDataInDateRangeByDepartment(fromDate, toDate, user.getDepartment());
			break;
		}
		return reportsData;
	}
	
	private void setReportsData(Long userId, DashboardDto dto, List<Object[]> dashboardData, Boolean dataInDateRange) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Map<LocalDate, Integer> grievanceByDateMap = new TreeMap<LocalDate, Integer>();
		Map<String, Integer> grievanceByDeparmentMap = new TreeMap<String, Integer>();
		Map<String, Integer> grievanceByStatusMap = new TreeMap<String, Integer>();
		List<NameValue> grievanceByStatusList = new ArrayList<NameValue>();
		int overAllCount = 0;
		initializeGrievanceCount(dto);

		for (Object[] data: dashboardData) {

			LocalDate date = LocalDate.parse(data[0].toString(),formatter);
			Integer dateCount = Integer.parseInt(data[1].toString());
			String grievanceType = data[2].toString();
			String grievanceStatus = data[3].toString();
			overAllCount += dateCount;
			if(!grievanceByDateMap.containsKey(date)) {
				grievanceByDateMap.put(date, dateCount);
			} else {
				int updatedCount = grievanceByDateMap.get(date) + dateCount;
				grievanceByDateMap.put(date, updatedCount);
			}

			if(!grievanceByDeparmentMap.containsKey(grievanceType)) {
				grievanceByDeparmentMap.put(grievanceType, dateCount);
			} else {
				int updatedCount = grievanceByDeparmentMap.get(grievanceType) + dateCount;
				grievanceByDeparmentMap.put(grievanceType, updatedCount);
			}
			if(!grievanceByStatusMap.containsKey(grievanceStatus)) {
				grievanceByStatusMap.put(grievanceStatus, dateCount);
			} else {
				int updatedCount = grievanceByStatusMap.get(grievanceStatus) + dateCount;
				grievanceByStatusMap.put(grievanceStatus, updatedCount);
			}
			
		}
		
		List<NameValue>  grievanceByDateList = grievanceByDateMap.entrySet()
			    .stream()
			    .map(entry -> new NameValue(entry.getKey().toString(), entry.getValue().toString()))
			    .toList(); 
		
		
		List<NameValue>  grievanceByDeparmentList = grievanceByDeparmentMap.entrySet()
			    .stream()
			    .map(entry -> new NameValue(entry.getKey(), entry.getValue().toString()))
			    .toList(); 
		
		
		grievanceByStatusList = grievanceByStatusMap.entrySet()
			    .stream()
			    .map(entry -> {
			        NameValue nameValue = new NameValue(entry.getKey(), entry.getValue().toString());
			        return nameValue;
			    })
			    .toList();

		if(dataInDateRange) {
			dto.setGrievancesInDateRange(grievanceByDateList);
			dto.setGrievancesByDepartmentInDateRange(grievanceByDeparmentList);
			dto.setGrievancesByStatusInDateRange(grievanceByStatusList);			
		} else {
			User user = userRepository.findById(userId).get();
			dto.setOverallGrievancesByDate(grievanceByDateList);
			dto.setOverallGrievancesByDepartments(grievanceByDeparmentList);
			dto.setOverallGrievancesByStatus(grievanceByStatusList);			
			dto.setOverAllGrievanceCount(overAllCount);
		}
		setGrievanceCount(dto, grievanceByStatusList);
	}
	
	private void setGrievanceCount(DashboardDto dto, List<NameValue> grievanceByStatus) {
		
		for(NameValue nv: grievanceByStatus) {
			switch(nv.getName()) {
			case "New":
				dto.setNewGrievanceCount(Integer.parseInt(nv.getValue()));
				break;
			case "Assigned":
				dto.setAssignedGrievanceCount(Integer.parseInt(nv.getValue()));
				break;
			case "In Progress":
				dto.setInProgessGrievanceCount(Integer.parseInt(nv.getValue()));
				break;
			case "Declined":
				dto.setDeclinedGrievanceCount(Integer.parseInt(nv.getValue()));
				break;
			case "Resolved":
				dto.setResolvedGrievanceCount(Integer.parseInt(nv.getValue()));
				break;
		
			}
		}
	}
	private void initializeGrievanceCount(DashboardDto dto) {
		
		dto.setNewGrievanceCount(0);
		dto.setAssignedGrievanceCount(0);
		dto.setInProgessGrievanceCount(0);
		dto.setDeclinedGrievanceCount(0);
		dto.setResolvedGrievanceCount(0);
	}
	
	public List<Grievance> getByUserId(Long userId) {
		User user = userRepository.findById(userId).get();
		if(user != null) {
			if(user.getRole().equals("admin")) {
				return grievanceRepository.findAll();
			} else {
				grievanceRepository.getByUserId(userId);
			}
		}
		return null;
    }
	
	private Grievance newGrievance(String rowStatus, GrievanceDto dto) {
		Grievance grv = new Grievance(
				dto.getTitle(),
				dto.getDescription(),
				dto.getPriority(),
				dto.getGrievanceType(),
				dto.getStatus()
				);
		User u = new User(dto.getUserId());
		if(rowStatus.equals("INSERT")) {
			grv.setRaisedBy(u);
			grv.setRaisedOn(LocalDate.now());
		}
		if(rowStatus.equals("UPDATE") && (dto.getAssignedTo() != null)) {
			User assignedTo = new User(dto.getAssignedTo());
			grv.setAssignedTo(assignedTo);
		}
		if(dto.getRemarks() != null) {
			grv.setRemarks(dto.getRemarks());
		}
		grv.setUpdatedOn(LocalDate.now());
		grv.setUpdatedby(u);
		System.out.println(grv);
		return grv;
	}
	private Grievance updateGrievance(GrievanceUpdateDto dto) {
		Grievance grv = grievanceRepository.getById(dto.getId());
		grv.setTitle(dto.getTitle());
		grv.setDescription(dto.getDescription());
		grv.setGrievanceType(dto.getGrievanceType());
		grv.setPriority(dto.getPriority());
		grv.setUpdatedOn(LocalDate.now());
		grv.setUpdatedby(new User(dto.getUserId()));
		grv.setRemarks(dto.getRemarks());
		return grv;
	}
	
	private GrievanceHistory getGrievanceHistory(String status, Grievance g) {
		GrievanceHistory grvhistory = new GrievanceHistory();
		grvhistory.setGrievanceId(g.getId());
		grvhistory.setDescription(g.getDescription());
		grvhistory.setTitle(g.getTitle());
		grvhistory.setPriority(g.getPriority());
		grvhistory.setStatus(g.getStatus());
		grvhistory.setGrievanceType(g.getGrievanceType());
		grvhistory.setAssignedTo(g.getAssignedTo());
		grvhistory.setModifiedBy(g.getUpdatedby());
		grvhistory.setChangeType(status);
		grvhistory.setModifiedOn(LocalDate.now());
		grvhistory.setRemarks(g.getRemarks());
		return grvhistory;
	}
	
	private GrievanceHistoryDto getGrievanceHistory(Grievance grv, GrievanceHistory gh) {
		GrievanceHistoryDto dto = new GrievanceHistoryDto();
		dto.setId(gh.getId());
		dto.setRaisedBy(grv.getRaisedBy().getFirstName() +" "+ grv.getRaisedBy().getLastName());
		dto.setRaisedOn(grv.getRaisedOn());
		if(gh.getAssignedTo() != null) {
			dto.setAssignedTo(gh.getAssignedTo().getFirstName() + " " + gh.getAssignedTo().getLastName() );
		}
		dto.setTitle(gh.getTitle());
		dto.setDescription(gh.getDescription());
		dto.setModifiedBy(gh.getModifiedBy().getFirstName() + " " + gh.getModifiedBy().getLastName() );
		dto.setPriority(gh.getPriority());
		dto.setGrievanceType(gh.getGrievanceType());
		dto.setStatus(gh.getStatus());
		dto.setRemarks(gh.getRemarks());
		return dto;
	}
	
	private Grievance updateAssignation(GrievanceAssignDto dto) {
		Grievance grievance = grievanceRepository.findById(dto.getGrievanceId()).get();
		grievance.setPriority(dto.getPriority());
		grievance.setGrievanceType(dto.getGrievanceType());
		grievance.setRemarks(dto.getRemarks());
		User assignedTo = userRepository.findById(dto.getAssignedTo()).get();
		grievance.setAssignedTo(assignedTo);
		User assignedBy = userRepository.findById(dto.getAssignedBy()).get();
		grievance.setUpdatedby(assignedBy);
		grievance.setUpdatedOn(LocalDate.now());	
		grievance.setStatus(dto.getStatus());
		return grievance;
	}
	

}
