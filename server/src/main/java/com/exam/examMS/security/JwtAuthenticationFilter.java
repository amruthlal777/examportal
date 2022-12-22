package com.exam.examMS.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.examMS.service.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	private final JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

          final String requestTokenHeader = request.getHeader("Authorization");
          
          String username = null;
          String jwtToken=null;
          
          if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer "))
          {
        	  jwtToken = requestTokenHeader.substring(7);
        	  
        	  try {
        	  username = this.jwtUtil.extractUsername(jwtToken);
        	  }catch (ExpiredJwtException e) {
				e.printStackTrace();
			}
          }
          else
          {
        	  System.out.println("Invalid token");
          }
          
          if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null)
          {
        	  final UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);
        	  
        	  if(this.jwtUtil.validateToken(jwtToken, userDetails))
        	  {
        		  UsernamePasswordAuthenticationToken usernamePasswordAuthentication 
        		  = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        		  
        		  usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        		  
        		  SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
        	  }
          }
          else
          {
        	  System.out.println("Token is not valid");
          }
          
          filterChain.doFilter(request, response);
	}

}
