package com.bv.gms.service;

import java.io.Console;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bv.gms.dto.GrievanceAssignDto;
import com.bv.gms.dto.GrievanceDto;
import com.bv.gms.dto.GrievanceHistoryDto;
import com.bv.gms.dto.GrievanceUpdateDto;
import com.bv.gms.entities.Grievance;
import com.bv.gms.entities.GrievanceHistory;
import com.bv.gms.entities.User;
import com.bv.gms.exceptions.BusinessException;
import com.bv.gms.repositories.GrievanceHistoryRepository;
import com.bv.gms.repositories.GrievanceRepository;
import com.bv.gms.repositories.UserRepository;

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
