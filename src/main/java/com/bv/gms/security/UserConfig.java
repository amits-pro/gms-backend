package com.bv.gms.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.bv.gms.repositories.UserRepository;

@Configuration
public class UserConfig<GmsUserDetailService> {

	
	
	@Autowired
	UserRepository userRepository;
	
	
	
	
    
  
}	

