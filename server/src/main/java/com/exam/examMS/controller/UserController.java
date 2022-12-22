package com.exam.examMS.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examMS.entity.Role;
import com.exam.examMS.entity.User;
import com.exam.examMS.entity.UserRole;
import com.exam.examMS.service.MailService;
import com.exam.examMS.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	private final UserService userService;
	private final MailService mailService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/test")
	public String test()
	{
		mailService.sendMail();
		return "successfully launched app";
	}
	@PostMapping("/createUser")
	public User createUser(@RequestBody User user) throws Exception
	{
		System.out.println("inside1");
		
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");
		
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		Set<UserRole> roles = new HashSet<UserRole>();
		roles.add(userRole);
		
		for(UserRole ur : roles)
		{
			System.out.println(ur);
		}
		return this.userService.createUser(user, roles);
	}
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username)
	{		
		return this.userService.getuser(username);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId)
	{		
		 this.userService.deleteUser(userId);
	}
	
}
