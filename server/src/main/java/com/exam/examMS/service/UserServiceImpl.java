package com.exam.examMS.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.examMS.entity.User;
import com.exam.examMS.entity.UserRole;
import com.exam.examMS.repo.RoleRepository;
import com.exam.examMS.repo.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	
	/*user creation*/
	@Override
	public User createUser(User user, Set<UserRole> role) throws Exception {
		
		User local = this.userRepository.findByUsername(user.getUsername());
		
		if(local != null)
		{
			System.out.println("User already present in Database");
			throw new Exception("User already present in Database");																																																																																																																																																																																																																																																																																																																																																																																																																																																																																	
		}
		else
		{	
			 for(UserRole userRole: role)
			 {
				 roleRepository.save(userRole.getRole());
			 }
			 
			 user.setUserRole(role);
			 
			 local = this.userRepository.save(user);
		}
		return local;
	}

	@Override
	public User getuser(String username) {

		return this.userRepository.findByUsername(username);
	}

	@Override
	public void deleteUser(Long id) {

		this.userRepository.deleteById(id);
	}

}
