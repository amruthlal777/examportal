package com.exam.examMS.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="EX_USER")
public class User implements UserDetails{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)	
	 private Long userId;
	 private String username;
	 private String password;
	 private String firstName;
	 private String lastName;
	 private String email;
	 private String phone;
	 private boolean enabled = true;
	 private String profile;
	 
	 @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
	 @JsonIgnore
	 private Set<UserRole> userRole=new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Set<Authority> set = new HashSet<Authority>();
		
		this.userRole.forEach(userRole->{
			set.add(new Authority(userRole.getRole().getRoleName()));
		});	
		
		return set;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	 
	 
}
