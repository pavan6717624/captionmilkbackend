package com.captionmilk.service;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.captionmilk.domain.LoginDetails;
import com.captionmilk.domain.OTP;
import com.captionmilk.model.StatusDTO;
import com.captionmilk.repository.LoginDetailsRepository;
import com.captionmilk.repository.OTPRepository;
import com.captionmilk.repository.RolesRepository;

@Service
public class OTPService {
	
	@Autowired
	OTPRepository otpRepository;
	
	@Autowired
	UtilService utilService;
	
	@Autowired
	LoginDetailsRepository loginRepository;
	
	@Autowired
	RolesRepository rolesRepository;
	
	
	public String generateOTP(Integer length)
	{
		 String numbers = "1234567890";
		 SecureRandom random = new SecureRandom();
	      char[] otp = new char[length];
	  
	   
	      for(int i = 0; i< length ; i++) {
	    	  otp[i] = numbers.charAt(random.nextInt(numbers.length()));
	      }
	     
	 return new String(otp);
	}
	
	public StatusDTO checkCustomerDetails(Long mobileNumber, String email)
	{
		StatusDTO status=new StatusDTO(true,"");
		
		
		 
		Optional<LoginDetails> login=loginRepository.findByContact(mobileNumber);
		if(login.isPresent())
		{
			status.setStatus(false);
			status.setMessage("Provided Mobile Number already Exists.\n");
		}
		
		login=loginRepository.findByEmail(email);
		if(login.isPresent())
		{
			status.setStatus(false);
			status.setMessage(status.getMessage()+"Provided Email already Exists.");
		}
		
		return status;
		
	}
	
	@Transactional
	public StatusDTO sendOTP(Long mobileNumber, String email, String name)
	{
		
		
		StatusDTO status=checkCustomerDetails(mobileNumber,email);
		
		if(!status.getStatus())
			return status;
		 
		 OTP otp=new OTP();
		 
		 Optional<OTP> checkOTP=otpRepository.getOTP(mobileNumber, Timestamp.valueOf(LocalDateTime.now()));
		 
		 if(checkOTP.isPresent())
		 otp=checkOTP.get();
		 else
		 {		 
		 otp.setOtp(generateOTP(4));
		 otp.setSentOn(Timestamp.valueOf(LocalDateTime.now()));
		 otp.setValidTill(Timestamp.valueOf(LocalDateTime.now().plusMinutes(15)));
		 otp.setMobile(mobileNumber);
		 otp.setValidated(false);
		 otp.setEmail(email);
		 otp.setName(name);
		 otpRepository.save(otp);
		 
		 utilService.sendMessage(email, "Caption Milk - Your OTP is "+otp.getOtp(), "Caption Milk -  Your OTP is "+otp.getOtp());
		 }
		 System.out.println(otp);
		 if(otp.getId()!=null)
			 {
			 status.setStatus(true);
			 status.setMessage("Success");
			 }
		 else
		 {
			 status.setStatus(false);
			 status.setMessage("Please provide Valid Details.");
		 }
		 return status;
	}

	@Transactional
	public StatusDTO verifyOTP(Long mobileNumber, String otp)
	{
		StatusDTO status = new StatusDTO();
		
		Optional<OTP> checkOTP=otpRepository.checkOTP(mobileNumber, Timestamp.valueOf(LocalDateTime.now()), otp);
		
		if(checkOTP.isPresent())
		{
			checkOTP.get().setValidated(true);
			otpRepository.save(checkOTP.get());
			LoginDetails login=new LoginDetails();
			login.setContact(checkOTP.get().getMobile());
			login.setEmail(checkOTP.get().getEmail());
			login.setName(checkOTP.get().getName());
			login.setIsDeleted(false);
			login.setIsDisabled(false);
			login.setJoinDate(Timestamp.valueOf(LocalDateTime.now()));
			login.setRole(rolesRepository.findByRoleName("Customer"));
			loginRepository.save(login);
			
			status.setStatus(true);
			status.setMessage("Registration Successful.");
			
		}
		else
		{
			status.setStatus(false);
			status.setMessage("Please provide Valid OTP.");
		}
		 
		return status;

	}
	@Transactional
	public StatusDTO verifyLoginOTP(Long mobileNumber, String otp)
	{
		StatusDTO status = new StatusDTO();
		
		Optional<OTP> checkOTP=otpRepository.checkOTP(mobileNumber, Timestamp.valueOf(LocalDateTime.now()), otp);
		
		if(checkOTP.isPresent())
		{
			checkOTP.get().setValidated(true);
			otpRepository.save(checkOTP.get());
			status.setStatus(true);
			status.setMessage("Login Successful.");
			
		}
		else
		{
			status.setStatus(false);
			status.setMessage("Please provide Valid OTP.");
		}
		 
		return status;

	}

	@Transactional
	public StatusDTO sendLoginOTP(Long mobileNumber)
	{
		
		StatusDTO status = new StatusDTO();
		
		 
		 LoginDetails login=new LoginDetails();
		 
		 Optional<LoginDetails> checkLogin=loginRepository.findByContact(mobileNumber);
		 
		 if(checkLogin.isPresent())
		 {
			 login=checkLogin.get();
			 
			 OTP otp=new OTP();
			 otp.setOtp(generateOTP(4));
			 otp.setSentOn(Timestamp.valueOf(LocalDateTime.now()));
			 otp.setValidTill(Timestamp.valueOf(LocalDateTime.now().plusMinutes(15)));
			 otp.setMobile(mobileNumber);
			 otp.setValidated(false);
			 otp.setEmail(login.getEmail());
			 otp.setName(login.getName());
			 otpRepository.save(otp);
			 
			 utilService.sendMessage(login.getEmail(), "Caption Milk - Your OTP is "+otp.getOtp(), "Caption Milk -  Your OTP is "+otp.getOtp());
			 
			 
			 status.setStatus(true);
			 status.setMessage("Success");
			 }
		 else
		 {
			 status.setStatus(false);
			 status.setMessage("Provided Mobile Number is not Registered.\nPlease Register First and then Login.");
		 }
		 return status;
	}
	
	
}
