package com.captionmilk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.captionmilk.repository.LoginDetailsRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	LoginDetailsRepository loginDetailsRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("entered in  loadUserByUsername..." + username);
		
		Optional<com.captionmilk.domain.LoginDetails> user = loginDetailsRepository.findByContact(Long.valueOf(username));
		
		System.out.println(user);
		
		List<GrantedAuthority> roles =new ArrayList<GrantedAuthority>();
		
		if (user.isPresent()) {
			System.out.println("User is " +user.get().toString()+" "+user.get().getRole().getRoleName());
			roles.add(new SimpleGrantedAuthority(user.get().getRole().getRoleName()));
			return new User(user.get().getContact()+"", user.get().getContact()+"",roles);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
	}
}
