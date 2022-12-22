package com.exam.examMS.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.examMS.entity.Role;

@Repository	
public interface RoleRepository extends JpaRepository<Role, Long>{

}
