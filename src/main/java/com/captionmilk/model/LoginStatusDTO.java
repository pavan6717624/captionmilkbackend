package com.captionmilk.model;

import lombok.Data;

@Data
public class LoginStatusDTO {
	
	Long contact=0l;
	String userType="NONE";
	Boolean status=false;
	String jwt="";
	String message = "";
	
	public LoginStatusDTO()
	{
		
	}


	public LoginStatusDTO(Long contact, String userType, Boolean status, String jwt, String message) {
		
		this.contact = contact;
		this.userType = userType;
		this.status = status;
		this.jwt = jwt;
		this.message=message;
	}
	
	

}
