package com.captionmilk.controller;


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

import com.captionmilk.jwt.JwtTokenUtil;
import com.captionmilk.model.LoginStatusDTO;
import com.captionmilk.model.StatusDTO;
import com.captionmilk.repository.LoginDetailsRepository;
import com.captionmilk.service.JwtUserDetailsService;
import com.captionmilk.service.OTPService;




@RestController
@CrossOrigin(origins = "*")
public class Controller {
	
	@Autowired
	OTPService otpService; 
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	LoginDetailsRepository loginDetailsRepository;
	
	
	@RequestMapping(value = "sendOTP")
	public StatusDTO sendOTP(@RequestParam("mobile") String mobile,@RequestParam("email") String email,@RequestParam("name") String name)
	{
		try
		{
		return otpService.sendOTP(Long.valueOf(mobile),email,name);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return new StatusDTO(false,"Please provide Valid Details.");
		}
	}
	
	
	@RequestMapping(value = "sendLoginOTP")
	public StatusDTO sendLoginOTP(@RequestParam("mobile") String mobile)
	{
		try
		{
		return otpService.sendLoginOTP(Long.valueOf(mobile));
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return new StatusDTO(false,"Please provide Valid Details.");
		}
	}
	
	@RequestMapping(value = "verifyOTP")
	public StatusDTO verifyOTP(@RequestParam("mobile") String mobile,@RequestParam("otp") String otp)
	{
		try
		{
		return otpService.verifyOTP(Long.valueOf(mobile), otp);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return new StatusDTO(false,"Please provide Valid OTP.");
		}
	}
	
	@RequestMapping(value = "verifyLoginOTP")
	public LoginStatusDTO verifyLoginOTP(@RequestParam("mobile") String mobile,@RequestParam("otp") String otp)
	{
		
		
		LoginStatusDTO loginStatus=new LoginStatusDTO();
		
		try
		{
			StatusDTO status = otpService.verifyLoginOTP(Long.valueOf(mobile), otp);
			if(status.getStatus())
			{
				authenticate(mobile, mobile);
				final UserDetails loginDetails = userDetailsService
						.loadUserByUsername(mobile);

				final String token = jwtTokenUtil.generateToken(loginDetails);
				
				System.out.println("Token is "+token);
				
				loginStatus = new LoginStatusDTO(Long.valueOf(loginDetails.getUsername()), loginDetails.getAuthorities().toArray()[0].toString(), true, token ,"Login Successful!");
			}
			else
			{
				loginStatus = new LoginStatusDTO(0l, "", false, "","Please provide VALID OTP.");
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			loginStatus = new LoginStatusDTO(0l, "", false, "","Please provide VALID OTP.");
		}
		
		return loginStatus;
	}
	
	
	private void authenticate(String username, String password) throws Exception {
		//	System.out.println("entered in authenticate sub function...");
			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			} catch (DisabledException e) {
				throw new Exception("USER_DISABLED", e);
			} catch (BadCredentialsException e) {
				throw new Exception("INVALID_CREDENTIALS", e);
			}
			//System.out.println("exited in authenticate sub function...");
		}
	
	
	@RequestMapping(value = "/getLoginDetails")
	public LoginStatusDTO getLoginDetails() throws Exception {
		
		LoginStatusDTO loginStatus=new LoginStatusDTO();
		
		 if(SecurityContextHolder.getContext().getAuthentication() == null)
		 {
			
				
				loginStatus.setStatus(false);
				loginStatus.setUserType("");
		 }
		 else
		 {
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		loginStatus.setContact(Long.valueOf(userDetails.getUsername()));
		
		loginStatus.setStatus(true);
		
		System.out.println(Long.valueOf(userDetails.getUsername()));
			
		loginStatus.setUserType(userDetails.getAuthorities().toArray()[0].toString());
		 };
	
		return loginStatus;
	}
	
 

}
