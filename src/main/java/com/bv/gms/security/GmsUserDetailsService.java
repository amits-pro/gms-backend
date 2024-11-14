package com.bv.gms.security;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bv.gms.entities.User;
import com.bv.gms.repositories.UserRepository;

@Service
public class GmsUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // Your UserRepository for DB access
    

	/*
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException {
	 * 
	 * User user = userRepository.findByUserId(username); System.out.println(user);
	 * if(user != null) { return new
	 * org.springframework.security.core.userdetails.User(username,
	 * passwordEncoder.encode(user.getPassword()), new ArrayList<>()); } else {
	 * throw new UsernameNotFoundException("User not found"); }
	 * 
	 * }
	 */
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("User name not found: " + username));
        
        System.out.println(user);

        return user;

    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Recommended password encoder
    }

}