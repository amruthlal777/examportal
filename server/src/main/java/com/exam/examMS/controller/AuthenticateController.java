package com.exam.examMS.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examMS.entity.JwtRequest;
import com.exam.examMS.entity.JwtResponse;
import com.exam.examMS.entity.User;
import com.exam.examMS.security.JwtUtil;
import com.exam.examMS.service.UserDetailsServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class AuthenticateController {

	private final AuthenticationManager authenticationManager;
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	private final JwtUtil jwtUtil;

	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		System.out.println("inside");
		 	try {
				
		 		authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
		 		
			} catch (UsernameNotFoundException e) {
				throw new Exception("Username is not found");
			}
		 	
		 	UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
		 	
		 	String token = this.jwtUtil.generateToken(userDetails);
		 	
		 	return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String username, String password) throws Exception {
		
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		} catch (DisabledException e) {

			throw new Exception("USER_DISABLED");
		}
		catch (BadCredentialsException e) {

			throw new Exception("INVALID_CREDENTIALS"); 	
		}
	}
	
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal)
	{
		return (User)this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
	}
}
