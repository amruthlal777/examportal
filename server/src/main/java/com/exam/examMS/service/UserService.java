package com.exam.examMS.service;

import java.util.Set;

import com.exam.examMS.entity.User;
import com.exam.examMS.entity.UserRole;

public interface UserService {

	public User createUser(User user,Set<UserRole> role) throws Exception;
	
	public User getuser(String username);
	
	public void deleteUser(Long id);
}
