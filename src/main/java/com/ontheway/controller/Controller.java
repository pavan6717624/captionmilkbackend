package com.ontheway.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ontheway.domain.City;
import com.ontheway.jwt.JwtTokenUtil;
import com.ontheway.model.DisplayHotels;
import com.ontheway.model.DisplayItems;
import com.ontheway.model.LoginStatusDTO;
import com.ontheway.repository.RolesRepository;
import com.ontheway.service.CustomerService;
import com.ontheway.service.JwtUserDetailsService;




@RestController
@CrossOrigin(origins = "*")
public class Controller {
	
	
	
	
	@Autowired
	RolesRepository rolesRepository;
	
	
	

	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "getCities")
	public List<City> getCities()
	{
		return customerService.getCities();
	}
	
	@RequestMapping(value = "getHotels")
	public List<DisplayHotels> getHotels(@RequestParam("fromCity") String fromCity, @RequestParam("toCity") String toCity,@RequestParam("orderType") String orderType,@RequestParam("distance") String distance)
	{
		
		return customerService.getHotels(fromCity,toCity,orderType,distance);
	}
	
	@RequestMapping(value = "getItems")
	public List<DisplayItems> getItems(@RequestParam("hotelId") String hotelId)
	{
		
		return customerService.getItems(hotelId);
	}
	
 	@RequestMapping(value = "/login")
 	public LoginStatusDTO createAuthenticationToken(@RequestParam("username") String username, @RequestParam("password") String password) {
 		System.out.println("entered in authenticate...");
 		LoginStatusDTO loginStatus=new LoginStatusDTO();
 		try
 		{
 			username=username.toLowerCase().replaceAll("to","");
			
 		authenticate(username, password);

 		final UserDetails userDetails = userDetailsService
 				.loadUserByUsername(username);

 		final String token = jwtTokenUtil.generateToken(userDetails);
 		System.out.println("exited in authenticate...");
		
		
		
 		loginStatus.setUserId(userDetails.getUsername());
		
 		loginStatus.setLoginStatus(true);
		
 		loginStatus.setJwt(token);
			
 		loginStatus.setUserType(userDetails.getAuthorities().toArray()[0].toString());
 		}
 		catch(Exception ex)
 		{
 			System.out.println("Error Occured while logging in "+ex);
 		}
	
 		return loginStatus;
 	}
	

	
	@RequestMapping(value = "/getLoginDetails")
	public LoginStatusDTO getLoginDetails() throws Exception {
		
		LoginStatusDTO loginStatus=new LoginStatusDTO();
		
		 if(SecurityContextHolder.getContext().getAuthentication() == null)
		 {
			 loginStatus.setUserId("");
				
				loginStatus.setLoginStatus(false);
					
				loginStatus.setUserType("");
		 }
		 else
		 {
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		loginStatus.setUserId(userDetails.getUsername());
		
		loginStatus.setLoginStatus(true);
			
		loginStatus.setUserType(userDetails.getAuthorities().toArray()[0].toString());
		 }
	
		return loginStatus;
	}
	
	

	private void authenticate(String username, String password) throws Exception {
		System.out.println("entered in authenticate sub function...");
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		System.out.println("exited in authenticate sub function...");
	}

	

}
