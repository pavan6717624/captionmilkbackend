package com.captionmilk.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.captionmilk.model.StatusDTO;
import com.captionmilk.service.OTPService;




@RestController
@CrossOrigin(origins = "*")
public class Controller {
	
	@Autowired
	OTPService otpService; 
	
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
	public StatusDTO verifyLoginOTP(@RequestParam("mobile") String mobile,@RequestParam("otp") String otp)
	{
		try
		{
		return otpService.verifyLoginOTP(Long.valueOf(mobile), otp);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return new StatusDTO(false,"Please provide Valid OTP.");
		}
	}
 

}
