package com.exam.examMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication
@ConfigurationPropertiesScan

public class ExamMsApplication{/* implements CommandLineRunner{*/
 	
//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(ExamMsApplication.class, args);
		
	}

//	@Override
//	public void run(String... args) throws Exception
//	{
//		System.out.println("starting the code");
//		
//		User user = new User();
//		
//		user.setFirstName("admin");
//		user.setLastName("user");
//		user.setUsername("admin");
//		user.setPassword(this.bCryptPasswordEncoder.encode("admin"));
//		user.setEmail("examportal2023@gmail.com");
//		user.setProfile("profile.png");
//		
//		Role role1 = new Role();
//		role1.setRoleId(1L);
//		role1.setRoleName("ADMIN");
//		
//		Set<UserRole> userRoleSet = new HashSet<>();
//		UserRole userRole = new UserRole();
//		
//		userRole.setRole(role1);
//		userRole.setUser(user);
//		userRoleSet.add(userRole);
//		
//		User user1 = this.userService.createUser(user, userRoleSet);
//		System.out.println(user1.getUsername());
//		
//	} 
}
