 package com.bv.gms.controllers;
 import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.*;

import com.bv.gms.dto.GrievanceAssignDto;
import com.bv.gms.dto.GrievanceDto;
import com.bv.gms.dto.GrievanceHistoryDto;
import com.bv.gms.dto.GrievanceOfficerDto;
import com.bv.gms.dto.GrievanceUpdateDto;
import com.bv.gms.entities.Grievance;
import com.bv.gms.entities.User;
import com.bv.gms.service.GrievanceService;
import com.bv.gms.service.UserService;

import jakarta.validation.Valid; 
 
@RestController
@CrossOrigin("*")  // Allow only requests from localhost:3000 (React/Angular front-end)
public class GrievanceController {
	
	@Autowired 
	GrievanceService grievanceService;

	@Autowired 
	UserService userService;

	 	
	 @PostMapping("/grievances") 
	 public ResponseEntity<Grievance> save(@Valid @RequestBody GrievanceDto grievance) 
	 
	 {
		 System.out.println("Grievance insert:" + grievance);
		 return ResponseEntity.ok(grievanceService.insert(grievance));
	 }
	 
	 @PutMapping("/grievances") 
	 public ResponseEntity<Grievance> update(@Valid @RequestBody GrievanceUpdateDto grievance) 
	 {
		 return ResponseEntity.ok(grievanceService.update(grievance));
	 }
	 
	 @PutMapping("/grievance-assign") 
	 public ResponseEntity<Grievance> assign(@Valid @RequestBody GrievanceAssignDto grievance) 
	 {
		 return ResponseEntity.ok(grievanceService.assign(grievance));
	 }
	 
	 @GetMapping("/grievance-hist") 
	 public ResponseEntity<List<GrievanceHistoryDto>> getHist(@RequestParam Long id) 
	 {
		 return ResponseEntity.ok(grievanceService.getHistory(id));
	 }
	 
	 @GetMapping("/grievance-officers") 
	 public ResponseEntity<List<GrievanceOfficerDto>> getOfficers(@RequestParam String grievanceType) 
	 {
		 return ResponseEntity.ok(userService.findOfficersByGrievanceType(grievanceType));
	 }
}
				 
				 
				 
