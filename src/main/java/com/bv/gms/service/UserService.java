package com.bv.gms.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bv.gms.dto.GrievanceDto;
import com.bv.gms.dto.GrievanceOfficerDto;
import com.bv.gms.dto.GrievanceResponseDto;
import com.bv.gms.dto.LoginDto;
import com.bv.gms.dto.UpdateUserProfileDto;
import com.bv.gms.dto.UserProfileDto;
import com.bv.gms.dto.UserRegistrationDto;
import com.bv.gms.entities.Grievance;
import com.bv.gms.entities.User;
import com.bv.gms.repositories.GrievanceRepository;
import com.bv.gms.repositories.UserRepository;
import com.bv.gms.security.GmsUserDetailsService;
import com.bv.gms.security.JwtUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UserService {
    
	@Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private GmsUserDetailsService userDetailsService;

	
	@Autowired
    private UserRepository userRepository;

	@Autowired
    private GrievanceRepository grievanceRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    @Autowired
    private JwtUtil jwtUtil;


    public User registerUser(UserRegistrationDto dto) {
        User user = populateUser(dto);
    	return userRepository.save(user);
    }
    
    public UserProfileDto updateProfile(UpdateUserProfileDto dto) {
        User user = userRepository.findById(dto.getUserId()).get();
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
    	user = userRepository.save(user);
        return populateProfile(user);
    }


    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
    
    public List<GrievanceOfficerDto> findOfficersByGrievanceType(String grievanceType) {
    	// Define a predicate to filter even numbers
        List<User> officers = userRepository.getUserMappedToGrievanceType(grievanceType);
        List<GrievanceOfficerDto> dtos = new ArrayList<GrievanceOfficerDto>();
        for(User u: officers) {
        	dtos.add(populateOfficer(u));
        }
        return dtos;
    }
    
    public String authenticateAndGenerateToken(LoginDto loginDto) throws Exception {
        
    	System.out.println("user controlller");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUserId(), loginDto.getPassword())
        );
        // Load the user
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUserId());
        System.out.println("user details");
        // Generate the JWT token
        return jwtUtil.generateToken(userDetails);
    }

    
    public List<GrievanceResponseDto> getAll(Long userId) {
		
		User user = userRepository.findById(userId).get();
		List<GrievanceResponseDto> dtos = new ArrayList<GrievanceResponseDto>();
		List<Grievance> grievances = null;
		
		switch(user.getRole()) {
			case "Grievance Controller":
				grievances = grievanceRepository.findAll();
				break;
			case "Grievance Officer":
			case "Grievance Supervisor":
				grievances = grievanceRepository.getAssignedToUserId(userId);
				break;
			case "Faculty":
			case "Student":
		        grievances = user.getGrievances();
				break;
		}
		
        for(Grievance g: grievances) {
        	dtos.add(getGrievanceDto(g));
        }
        return dtos;

    }
    
    public UserProfileDto getProfile(Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow();
        return populateProfile(user);
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
	private UserProfileDto populateProfile(User user) {
		UserProfileDto dto = new UserProfileDto();
		dto.setEmail(user.getEmail());
		dto.setPhone(user.getPhone());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setRole(user.getRole());
		dto.setUserId(user.getUserId());
		return dto;
	}
	
	private User populateUser(UserRegistrationDto dto) {
		System.out.println(dto);
		User user = new User();
		user.setUserId(dto.getUserId());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setEmail(dto.getEmail());
		user.setPhone(dto.getPhone());
		user.setRole(dto.getRole());
		user.setDepartment(dto.getDepartment());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		return user;
	}

	
	private GrievanceOfficerDto populateOfficer(User user) {
		GrievanceOfficerDto dto = new GrievanceOfficerDto(user.getId(),
				user.getRole(),
				user.getFirstName() + " " + user.getLastName());
		return dto;
	}

}
