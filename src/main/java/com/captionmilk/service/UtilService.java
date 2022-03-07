package com.captionmilk.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.captionmilk.model.LoggedUser;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class UtilService {
	
	  @Autowired
	    private JavaMailSender javaMailSender;
	  
	  private final static String ACCOUNT_SID = "ACf482a1d636d1f7a1948262ea473b868c";
	   private final static String AUTH_ID =  System.getenv("TwilioKey")+"";	   
	   
	   static {
		      Twilio.init(ACCOUNT_SID, AUTH_ID);
		   }
		   
	   public static LoggedUser getUser()
	   {
		   LoggedUser user=new LoggedUser();
	   org.springframework.security.core.userdetails.UserDetails userDetails = (org.springframework.security.core.userdetails.UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 //  user.setRoles(userDetails.getAuthorities().toArray());
	   user.setLoginId(Long.valueOf(userDetails.getUsername()));
	   return user;
	   
	   }
	public String generatePassword(int length) throws NoSuchAlgorithmException {
	      String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	      String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
	      String specialCharacters = "!@#$";
	      String numbers = "1234567890";
	      String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
	      SecureRandom random = new SecureRandom();
	      char[] password = new char[length];

	      password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
	      password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
	      password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
	      password[3] = numbers.charAt(random.nextInt(numbers.length()));
	   
	      for(int i = 4; i< length ; i++) {
	         password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
	      }
	      return new String(password);
	   }
	
	
	
	
		 
		   public void sendSMS(String smsTo, String text){
			   
			   try
			   {
		      Message.creator(new PhoneNumber("+91"+smsTo), new PhoneNumber("+15128438283"),
		         text).create();
			   }
			   catch(Exception ex)
			   {
				System.out.println("Error in Sending SMS for "+smsTo+ex);   
			   }
		   }
		   
		   public void sendMessage(String mailTo, String subject, String text)
		   {
			   try
			   {
			   SimpleMailMessage msg = new SimpleMailMessage();
			     msg.setTo(mailTo);
 				msg.setFrom("support@thetakeoff.in");
			     msg.setSubject(subject);
			     msg.setText(text);
			     javaMailSender.send(msg);
			   }
			   catch(Exception ex)
			   {
				System.out.println("Error in Sending Mail for "+mailTo + "\n"+ex);   
			   }
		   }
		   
		   
		   public String getSHA(String input) throws NoSuchAlgorithmException
		    { 
		        // Static getInstance method is called with hashing SHA 
		        MessageDigest md = MessageDigest.getInstance("SHA-256"); 
		  
		        // digest() method called 
		        // to calculate message digest of an input 
		        // and return array of byte
		        return toHexString(md.digest(input.getBytes(StandardCharsets.UTF_8))); 
		    }
		    
		    public String toHexString(byte[] hash)
		    {
		        // Convert byte array into signum representation 
		        BigInteger number = new BigInteger(1, hash); 
		  
		        // Convert message digest into hex value 
		        StringBuilder hexString = new StringBuilder(number.toString(16)); 
		  
		        // Pad with leading zeros
		        while (hexString.length() < 64) 
		        { 
		            hexString.insert(0, '0'); 
		        } 
		  
		        return hexString.toString(); 
		    }


	

}
